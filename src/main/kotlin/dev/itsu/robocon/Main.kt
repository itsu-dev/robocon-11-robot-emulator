package dev.itsu.robocon

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.level.format.generic.BaseFullChunk
import cn.nukkit.math.Vector3
import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.TextFormat

class Main : PluginBase() {

    var running = true

    companion object {
        lateinit var INSTANCE: Main
    }

    override fun onEnable() {
        INSTANCE = this
        server.scheduler.scheduleAsyncTask(this, ConnectionManager)
        logger.info("${TextFormat.DARK_GREEN}Enabled.")
    }

    override fun onDisable() {
        running = false
    }

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {
        when (label) {
            "spawnrobot" -> {
                val player = sender as Player
                RobotManager.spawn(player.chunk as BaseFullChunk)

                player.teleport(Vector3(0.0, 10.0, 0.0))

                ConnectionManager.start()
                logger.info("${TextFormat.YELLOW} Process Started.")
            }
        }
        return true
    }

}