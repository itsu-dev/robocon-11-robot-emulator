package dev.itsu.robocon.packet.rpi

open class RaspberryPiPacket(var data: IntArray) {

    companion object {
        const val ROTATE_RIGHT_FORWARD = 0
        const val ROTATE_LEFT_RETURN = 1
        const val ROTATE_LOCKET = 2

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
    var data1 = intArrayOf(0, 0, 0, 0)
    var data2 = intArrayOf(0, 0, 0, 0)

    fun encode() {
        data[0] = packetId.toString()[0].toString().toInt()
        data[1] = packetId.toString()[1].toString().toInt()
        data[2] = randId.toString()[0].toString().toInt()
        data[3] = randId.toString()[1].toString().toInt()
        data[4] = randId.toString()[2].toString().toInt()
        data[5] = randId.toString()[3].toString().toInt()
        data[6] = direction
        data[7] = type
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
        packetId = (data[0].toString() + data[1].toString()).toInt()
        randId = (data[2].toString() + data[3].toString() + data[4].toString() + data[5].toString()).toInt()
        direction = data[6]
        type = data[7]
        data1 = intArrayOf(data[8], data[9], data[10], data[11])
        data2 = intArrayOf(data[12], data[13], data[14], data[15])
    }

}