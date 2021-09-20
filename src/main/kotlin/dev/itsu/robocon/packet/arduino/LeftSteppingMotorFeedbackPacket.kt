package dev.itsu.robocon.packet.arduino

class LeftSteppingMotorFeedbackPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 21
    }

}