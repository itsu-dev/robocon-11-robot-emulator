package dev.itsu.robocon.packet.rpi

class RightSteppingMotorPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 10
    }

}