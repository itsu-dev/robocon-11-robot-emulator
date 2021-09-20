package dev.itsu.robocon

import cn.nukkit.scheduler.AsyncTask
import cn.nukkit.utils.TextFormat
import dev.itsu.robocon.packet.arduino.ArduinoPacket
import dev.itsu.robocon.packet.rpi.*
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketAddress

class ConnectionManager(private val instance: Main) : AsyncTask() {

    private val receiveSocket = DatagramSocket(1234)
    private val sendSocket = DatagramSocket()
    private lateinit var socketAddress: SocketAddress
    private val inetAddress = InetAddress.getByName("172.20.1.1")
    private val eventListener = PacketEventListener(this)

    override fun onRun() {
        sendDirect("Transmission Start".toByteArray())
        while (instance.running && receive());
    }

    private fun receive(): Boolean {
        val buf = ByteArray(16)
        val pk = DatagramPacket(buf, buf.size)
        receiveSocket.receive(pk)
        socketAddress = pk.socketAddress

        sendStop()

        var message = ""
        buf.forEach { message += it.toString() }

        if (message.isEmpty()) return false

        sendId(message)
        processPacket(message)

        instance.logger.info("${TextFormat.BLUE}[RECEIVE] ${TextFormat.RESET}(${pk.length}) $message")
        return true
    }

    private fun processPacket(message: String) {
        val randId = message.substring(2, 6).toInt()
        val data = IntArray(RaspberryPiPacket.PACKET_LENGTH)

        message.forEachIndexed { index, c -> data[index] = c.toString().toInt() }

        val pk = RaspberryPiPacket(data)
        pk.decode()

        when (pk.packetId) {
            10 -> eventListener.onRightSteppingMotorPacket(RightSteppingMotorPacket(pk.data).also { it.decode() })
            20 -> eventListener.onLeftSteppingMotorPacket(LeftSteppingMotorPacket(pk.data).also { it.decode() })
            30 -> eventListener.onBothSteppingMotorPacket(BothSteppingMotorPacket(pk.data).also { it.decode() })
            40 -> eventListener.onMeasureDistanceToBallPacket(MeasureDistanceToBallPacket(pk.data).also { it.decode() })
            50 -> eventListener.onMeasureLineTracerPacket(MeasureLineTracerPacket(pk.data).also { it.decode() })
            60 -> eventListener.onUpperServoMotorPacket(UpperServoMotorPacket(pk.data).also { it.decode() })
            70 -> eventListener.onBottomServoMotorPacket(BottomServoMotorPacket(pk.data).also { it.decode() })
            80 -> eventListener.onMeasureNineAxisSensorPacket(MeasureNineAxisSensorPacket(pk.data).also { it.decode() })
        }
    }

    fun dataPacket(pk: ArduinoPacket) {
        pk.encode()

        var message = ""
        pk.data.forEach { message += it.toString() }
        sendMessage(message)
    }

    private fun sendStop() {
        sendDirect("Stop".toByteArray())
    }

    private fun sendId(message: String) {
        sendDirect(message.substring(2, 6).toByteArray())
    }

    private fun sendMessage(message: String) {
        val data = mutableListOf<Byte>()
        message.forEach {
            data.add(it.toString().toInt().toByte())
        }
        sendDirect(data.toByteArray())
    }

    private fun sendDirect(byteArray: ByteArray) {
        val pk = DatagramPacket(byteArray, byteArray.size, inetAddress, 4321)
        sendSocket.send(pk)
        instance.logger.info("${TextFormat.GREEN}[SEND] ${TextFormat.RESET}(${pk.length}) ${String(byteArray)}")
    }

}