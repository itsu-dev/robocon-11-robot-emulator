package dev.itsu.robocon.packet.arduino

class RightSteppingMotorAlertPacket() : ArduinoPacket(1000) {

    init {
        packetId = 10
    }

}