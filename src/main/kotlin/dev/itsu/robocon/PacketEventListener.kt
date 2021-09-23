package dev.itsu.robocon

import dev.itsu.robocon.packet.arduino.DistanceSensorResultPacket
import dev.itsu.robocon.packet.arduino.LineTracerResultPacket
import dev.itsu.robocon.packet.arduino.NineAxisSensorResultPacket
import dev.itsu.robocon.packet.rpi.*

class PacketEventListener(private val connectionManager: ConnectionManager) {

    fun onRightSteppingMotorPacket(pk: RightSteppingMotorPacket) {
        when (pk.direction) {
            RaspberryPiPacket.ROTATE_RIGHT_FORWARD, RaspberryPiPacket.ROTATE_LEFT_RETURN -> RobotManager.rotating = true
            RaspberryPiPacket.ROTATE_LOCKED -> RobotManager.rotationDirection = 0
        }
    }

    fun onLeftSteppingMotorPacket(pk: LeftSteppingMotorPacket) {
        when (pk.direction) {
            RaspberryPiPacket.ROTATE_RIGHT_FORWARD, RaspberryPiPacket.ROTATE_LEFT_RETURN -> RobotManager.rotating =  true
            RaspberryPiPacket.ROTATE_LOCKED -> RobotManager.rotationDirection = 1
        }
        if (pk.direction == RaspberryPiPacket.ROTATE_LOCKED) {
            RobotManager.rotationDirection = 1
        }
    }

    fun onBothSteppingMotorPacket(pk: BothSteppingMotorPacket) {
        if (pk.direction == RaspberryPiPacket.ROTATE_RIGHT_FORWARD) {
            RobotManager.moving = true
        } else if (pk.direction == RaspberryPiPacket.ROTATE_LOCKED) {
            RobotManager.moving = false
            RobotManager.rotating = false
        }
    }

    fun onMeasureDistanceToBallPacket(pk: MeasureDistanceToBallPacket) {
        val packet = DistanceSensorResultPacket(pk.randId)
        packet.distance = (RobotManager.distanceToFacingWall() * 100).toFloat()
        connectionManager.dataPacket(packet)
    }

    fun onMeasureLineTracerPacket(pk: MeasureLineTracerPacket) {
        val packet = LineTracerResultPacket(pk.randId)
        packet.isOnLine = RobotManager.isOnLine()
        connectionManager.dataPacket(packet)
    }

    fun onUpperServoMotorPacket(pk: UpperServoMotorPacket) {

    }

    fun onBottomServoMotorPacket(pk: BottomServoMotorPacket) {

    }

    fun onMeasureNineAxisSensorPacket(pk: MeasureNineAxisSensorPacket) {
        val packet = NineAxisSensorResultPacket(pk.randId)
        packet.zGeoMagnetism = RobotManager.getOriginalYaw().toFloat()
        connectionManager.dataPacket(packet)
    }

}