package dev.itsu.robocon.packet.arduino

class LineTracerResultPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 50
    }

}