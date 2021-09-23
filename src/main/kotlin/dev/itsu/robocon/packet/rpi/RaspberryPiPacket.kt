package dev.itsu.robocon.packet.rpi

open class RaspberryPiPacket(var data: ByteArray) {

    companion object {
        const val ROTATE_RIGHT_FORWARD = 0
        const val ROTATE_LEFT_RETURN = 1
        const val ROTATE_LOCKED = 2

        const val MOTOR_UNLOCKED = 0
        const val MOTOR_LOCKED = 1

        const val DATA_TYPE_1 = 0
        const val DATA_TYPE_2 = 1
        const val DATA_TYPE_3 = 2
        const val DATA_TYPE_4 = 3

        const val UNKNOWN_PACKET_ID = -1
        const val PACKET_LENGTH = 16
        const val DATA_LENGTH = 4
    }

    var packetId = UNKNOWN_PACKET_ID
    var randId = 1000
    var direction = ROTATE_RIGHT_FORWARD
    var type = DATA_TYPE_1
    var data1 = byteArrayOf(0x00, 0x00, 0x00, 0x00)
    var data2 = byteArrayOf(0x00, 0x00, 0x00, 0x00)

    fun encode() {
        data[0] = packetId.toString()[0].toString().toInt().toByte()
        data[1] = packetId.toString()[1].toString().toInt().toByte()
        data[2] = randId.toString()[0].toString().toInt().toByte()
        data[3] = randId.toString()[1].toString().toInt().toByte()
        data[4] = randId.toString()[2].toString().toInt().toByte()
        data[5] = randId.toString()[3].toString().toInt().toByte()
        data[6] = direction.toByte()
        data[7] = type.toByte()
        data[8] = data1[0]
        data[9] = data1[1]
        data[10] = data1[2]
        data[11] = data1[3]
        data[12] = data2[0]
        data[13] = data2[1]
        data[14] = data2[2]
        data[15] = data2[3]
    }

    fun decode() {
        packetId = (data[0].toInt().toString() + data[1].toInt().toString()).toInt()
        randId = (data[2].toInt().toString() + data[3].toInt().toString() + data[4].toInt().toString() + data[5].toInt().toString()).toInt()
        direction = data[6].toInt()
        type = data[7].toInt()
        data1 = byteArrayOf(data[8], data[9], data[10], data[11])
        data2 = byteArrayOf(data[12], data[13], data[14], data[15])
    }

}