package dev.itsu.robocon.packet.arduino

class NineAxisSensorResultPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 80
    }

}