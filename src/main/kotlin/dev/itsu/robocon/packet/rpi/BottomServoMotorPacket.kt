package dev.itsu.robocon.packet.rpi

class BottomServoMotorPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 70
    }

}