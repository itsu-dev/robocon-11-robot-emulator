package dev.itsu.robocon.packet.rpi

class UpperServoMotorPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 60
    }

}