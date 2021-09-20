package dev.itsu.robocon.packet.arduino

class BottomServoMotorFeedbackPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 70
    }

}