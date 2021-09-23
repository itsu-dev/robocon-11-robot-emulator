package dev.itsu.robocon.packet.arduino

class NineAxisSensorResultPacket(randId: Int) : ArduinoPacket(randId) {

    var xAcceleration = 0.0F
    var yAcceleration = 0.0F
    var zAcceleration = 0.0F
    var xRadPerMilliSec = 0.0F
    var yRadPerMilliSec = 0.0F
    var zRadPerMilliSec = 0.0F
    var xGeoMagnetism = 0.0F
    var yGeoMagnetism = 0.0F
    var zGeoMagnetism = 0.0F

    init {
        packetId = 80
    }

    override fun encodeData() {
        payload[0] = floatToArray(xAcceleration)
        payload[1] = floatToArray(yAcceleration)
        payload[2] = floatToArray(zAcceleration)
        payload[3] = floatToArray(xRadPerMilliSec)
        payload[4] = floatToArray(yRadPerMilliSec)
        payload[5] = floatToArray(zRadPerMilliSec)
        payload[6] = floatToArray(xGeoMagnetism)
        payload[7] = floatToArray(yGeoMagnetism)
        payload[8] = floatToArray(zGeoMagnetism)
    }

}