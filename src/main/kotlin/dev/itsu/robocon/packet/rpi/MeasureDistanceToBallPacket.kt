package dev.itsu.robocon.packet.rpi

class MeasureDistanceToBallPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 40
    }

}