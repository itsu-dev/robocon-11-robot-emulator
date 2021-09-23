package dev.itsu.robocon.packet.arduino

import dev.itsu.robocon.packet.rpi.RaspberryPiPacket

class BothSteppingMotorFeedbackPacket(randId: Int) : ArduinoPacket(randId) {

    var radPerMilliSec = 0.0F
    var radSum = 0.0F
    var time = 0.0F
    var dataType = RaspberryPiPacket.DATA_TYPE_1  // TYPE_2

    init {
        packetId = 31
    }

    override fun encodeData() {
        payload[0] = floatToArray(radPerMilliSec)
        payload[1] = floatToArray(
            if (dataType == RaspberryPiPacket.DATA_TYPE_1) radSum
            else time
        )
    }

}