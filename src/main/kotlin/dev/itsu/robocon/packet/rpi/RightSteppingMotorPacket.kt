package dev.itsu.robocon.packet.rpi

class RightSteppingMotorPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 10
    }

}