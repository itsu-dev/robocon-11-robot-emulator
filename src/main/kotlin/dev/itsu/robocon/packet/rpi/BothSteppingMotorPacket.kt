package dev.itsu.robocon.packet.rpi

class BothSteppingMotorPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 30
    }

}