package dev.itsu.robocon.packet.rpi

class MeasureLineTracerPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 50
    }

}