package dev.itsu.robocon.packet.rpi

class BothSteppingMotorPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 30
    }

}