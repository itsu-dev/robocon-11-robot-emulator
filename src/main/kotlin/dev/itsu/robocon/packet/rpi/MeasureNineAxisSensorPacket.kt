package dev.itsu.robocon.packet.rpi

class MeasureNineAxisSensorPacket(data: IntArray) : RaspberryPiPacket(data) {

    init {
        packetId = 80
    }

}