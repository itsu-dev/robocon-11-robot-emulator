package dev.itsu.robocon.packet.arduino

class LeftSteppingMotorAlertPacket() : ArduinoPacket(1000) {

    init {
        packetId = 20
    }

}