package dev.itsu.robocon.packet.rpi

class LeftSteppingMotorPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 20
    }

}