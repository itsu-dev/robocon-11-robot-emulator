package dev.itsu.robocon.packet.arduino

open class ArduinoPacket(var randId: Int) {

    companion object {
        const val PACKET_LENGTH = 42
        const val DATA_LENGTH = 36

        const val UNKNOWN_PACKET_ID = -1
        const val UNKNOWN_RANDOM_ID = 1000
    }

    var packetId = UNKNOWN_PACKET_ID
    var data = IntArray(PACKET_LENGTH)
    var payload = arrayOf(
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0)
    )

    fun encode() {
        data[0] = packetId.toString()[0].toString().toInt()
        data[1] = packetId.toString()[1].toString().toInt()
        data[2] = randId.toString()[0].toString().toInt()
        data[3] = randId.toString()[1].toString().toInt()
        data[4] = randId.toString()[2].toString().toInt()
        data[5] = randId.toString()[3].toString().toInt()

        for (i in 0..PACKET_LENGTH - 7) {
            payload.forEach {
                it.forEach {
                    data[i + 6] = it
                }
            }
        }
    }

}