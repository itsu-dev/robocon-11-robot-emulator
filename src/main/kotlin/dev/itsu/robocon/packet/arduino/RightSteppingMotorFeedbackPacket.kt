package dev.itsu.robocon.packet.arduino

class RightSteppingMotorFeedbackPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 11
    }

}