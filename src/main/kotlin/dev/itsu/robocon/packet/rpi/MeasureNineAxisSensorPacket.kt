package dev.itsu.robocon.packet.rpi

class MeasureNineAxisSensorPacket(data: ByteArray) : RaspberryPiPacket(data) {

    init {
        packetId = 80
    }

}