package dev.itsu.robocon.packet.arduino

class BothSteppingMotorAlertPacket : ArduinoPacket(1000) {

    init {
        packetId = 30
    }

}