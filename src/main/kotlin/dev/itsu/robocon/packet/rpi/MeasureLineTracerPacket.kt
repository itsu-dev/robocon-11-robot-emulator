package dev.itsu.robocon.packet.rpi

class MeasureLineTracerPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 50
    }

}