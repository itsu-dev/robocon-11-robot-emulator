package dev.itsu.robocon.packet.arduino

class DistanceSensorResultPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 40
    }

}