package dev.itsu.robocon

import cn.nukkit.block.BlockID
import cn.nukkit.entity.Entity
import cn.nukkit.entity.item.EntityMinecartEmpty
import cn.nukkit.level.Level
import cn.nukkit.level.Position
import cn.nukkit.level.format.generic.BaseFullChunk
import cn.nukkit.math.Vector3
import cn.nukkit.nbt.tag.CompoundTag
import cn.nukkit.utils.TextFormat
import java.lang.Math.toRadians
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

object RobotManager {

    private lateinit var robotEntity: EntityMinecartEmpty

    /*
    <<座標系>>
            北(z-, 180°)
    西(x-, 90°)  +  東(x+, 270°)
             南(z+, 0°)
     */

    // 座標系は実寸(mm) / 100
    // 初期位置に対面する壁から時計回りに1, 2, 3, 4
    const val WALL_N = 50.4  // 北壁z
    const val WALL_E = 33.6  // 東壁x
    const val WALL_S = 0.0   // 南壁z
    const val WALL_W = 0.0   // 西壁x

    // ロボットが動いているときのフラグ
    var moving = false

    // ロボットが回転しているときのフラグ
    var rotating = false

    // 回転の増分
    var rotationDelta = 10.0

    // 回転方向（0: 時計回り, 1: 反時計回り）
    var rotationDirection = 0

    // ロボット（に見立てたトロッコ）を出現させる
    fun spawn(chunk: BaseFullChunk) {
        robotEntity = EntityMinecartEmpty(chunk, Entity.getDefaultNBT(Vector3(7.0, 7.0, 7.0)))
        robotEntity.setPosition(Vector3(7.0, 7.0, 7.0))
        robotEntity.yaw = 180.0
        robotEntity.spawnToAll()

        Main.INSTANCE.logger.info("${TextFormat.YELLOW} Spawned.")

        // 移動ループ
        Main.INSTANCE.server.scheduler.scheduleRepeatingTask(Main.INSTANCE, {
            if (moving) {
                val next = getNextCoordinates()
                robotEntity.move(next.first, 0.0, next.second)
            }
        }, 20)

        // 回転ループ
        Main.INSTANCE.server.scheduler.scheduleRepeatingTask(Main.INSTANCE, {
            if (!moving && rotating) {
                when(rotationDirection) {
                    0 -> robotEntity.yaw += rotationDelta
                    1 -> robotEntity.yaw -= rotationDelta
                }
            }
        }, 20)
    }

    private fun getNextCoordinates(): Pair<Double, Double> {
        Main.INSTANCE.logger.info("" + robotEntity.x + ", " + robotEntity.z)
        val yaw = toRadians(robotEntity.yaw)
        val cos = cos(yaw)
        val sin = sin(yaw)
        val xSign = if (cos >= 0) -1 else 1
        val zSign = if (sin >= 0) -1 else 1
        return xSign * cos * cos * 1.2 to zSign * sin * sin * 1.2
    }

    // 対面する壁との距離を測定
    fun distanceToFacingWall(): Double {
        val yaw = robotEntity.yaw
        Main.INSTANCE.logger.info("" + robotEntity.z + ":" + robotEntity.yaw)
        return mapOf(
            abs(abs(yaw) % 360 - 180) to WALL_N - robotEntity.x,
            abs(abs(yaw) % 360 - 270) to WALL_E - robotEntity.z,
            abs(abs(yaw) % 360) to robotEntity.x,
            abs(abs(yaw) % 360 - 90) to robotEntity.z
        ).minByOrNull { it.key }?.value!!.also {
            Main.INSTANCE.logger.info("" + it + ":" + robotEntity.yaw)
        }
    }

    // ライン上かどうか
    fun isOnLine() =
        (robotEntity.level.getBlock(Vector3(robotEntity.x, robotEntity.y - 1, robotEntity.z)).id == BlockID.WOOL)

    // 北を0°としたときのロボットの方向（時計回り）
    fun getOriginalYaw() = robotEntity.yaw - 180

    // 東にx+をとったときの座標
    fun getOriginalX() = robotEntity.x

    // 北にy+をとったときの座標
    fun getOriginalY() = -robotEntity.z

}