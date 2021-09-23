package dev.itsu.robocon.packet.arduino

import dev.itsu.robocon.Main
import java.nio.ByteBuffer

open class ArduinoPacket(var randId: Int) {

    companion object {
        const val PACKET_LENGTH = 42
        const val DATA_LENGTH = 36

        const val UNKNOWN_PACKET_ID = -1
        const val UNKNOWN_RANDOM_ID = 1000
    }

    var packetId = UNKNOWN_PACKET_ID
    var data = ByteArray(PACKET_LENGTH)
    var payload = arrayOf(
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0),
        byteArrayOf(0, 0, 0, 0)
    )

    fun encode() {
        encodeData()

        val dataList = mutableListOf<Byte>()
        dataList.add(packetId.toString()[0].toString().toInt().toByte())
        dataList.add(packetId.toString()[1].toString().toInt().toByte())
        dataList.add(randId.toString()[0].toString().toInt().toByte())
        dataList.add(randId.toString()[1].toString().toInt().toByte())
        dataList.add(randId.toString()[2].toString().toInt().toByte())
        dataList.add(randId.toString()[3].toString().toInt().toByte())

        payload.forEach {
            dataList.addAll(it.toList())
        }

        data = dataList.toByteArray()
    }

    open fun encodeData() {

    }

    fun floatToArray(value: Float): ByteArray {
        return ByteBuffer.allocate(4).putFloat(value).array()
    }

}