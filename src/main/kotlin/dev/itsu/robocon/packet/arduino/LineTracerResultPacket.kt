package dev.itsu.robocon.packet.arduino

class LineTracerResultPacket(randId: Int) : ArduinoPacket(randId) {

    var isOnLine = false

    init {
        packetId = 50
    }

    override fun encodeData() {
        payload[0] = byteArrayOf(0x00, 0x00, 0x00, if (isOnLine) 0x01 else 0x00)
    }

}