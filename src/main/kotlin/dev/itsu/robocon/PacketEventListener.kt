package dev.itsu.robocon

import dev.itsu.robocon.packet.arduino.LineTracerResultPacket
import dev.itsu.robocon.packet.arduino.NineAxisSensorResultPacket
import dev.itsu.robocon.packet.rpi.*

class PacketEventListener(private val connectionManager: ConnectionManager) {

    fun onRightSteppingMotorPacket(pk: RightSteppingMotorPacket) {

    }

    fun onLeftSteppingMotorPacket(pk: LeftSteppingMotorPacket) {

    }

    fun onBothSteppingMotorPacket(pk: BothSteppingMotorPacket) {

    }

    fun onMeasureDistanceToBallPacket(pk: MeasureDistanceToBallPacket) {

    }

    fun onMeasureLineTracerPacket(pk: MeasureLineTracerPacket) {
        val packet = LineTracerResultPacket(pk.randId)
        connectionManager.dataPacket(packet)
    }

    fun onUpperServoMotorPacket(pk: UpperServoMotorPacket) {

    }

    fun onBottomServoMotorPacket(pk: BottomServoMotorPacket) {

    }

    fun onMeasureNineAxisSensorPacket(pk: MeasureNineAxisSensorPacket) {
        val packet = NineAxisSensorResultPacket(pk.randId)
        connectionManager.dataPacket(packet)
    }

}