package dev.itsu.robocon.packet.arduino

class UpperServoMotorFeedbackPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 60
    }

}