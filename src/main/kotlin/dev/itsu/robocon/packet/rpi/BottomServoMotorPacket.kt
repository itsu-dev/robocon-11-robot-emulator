package dev.itsu.robocon.packet.rpi

class BottomServoMotorPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 70
    }

}