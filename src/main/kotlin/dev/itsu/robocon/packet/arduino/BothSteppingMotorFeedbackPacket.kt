package dev.itsu.robocon.packet.arduino

class BothSteppingMotorFeedbackPacket(randId: Int) : ArduinoPacket(randId) {

    init {
        packetId = 31
    }

}