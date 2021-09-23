package dev.itsu.robocon.packet.rpi

class LeftSteppingMotorPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 20
    }

}