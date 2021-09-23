package dev.itsu.robocon.packet.rpi

class MeasureDistanceToBallPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 40
    }

}