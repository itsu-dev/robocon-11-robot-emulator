package dev.itsu.robocon.packet.arduino

class DistanceSensorResultPacket(randId: Int) : ArduinoPacket(randId) {

    var distance = 0.0F

    init {
        packetId = 40
    }

    override fun encodeData() {
        payload[0] = floatToArray(distance)
    }

}