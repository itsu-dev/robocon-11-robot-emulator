package dev.itsu.robocon.packet.rpi

class UpperServoMotorPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 60
    }

}