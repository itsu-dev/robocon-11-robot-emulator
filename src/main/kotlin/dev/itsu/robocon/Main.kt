package dev.itsu.robocon

import cn.nukkit.plugin.PluginBase
import cn.nukkit.scheduler.AsyncTask
import cn.nukkit.utils.TextFormat

class Main : PluginBase() {

    var running = true

    override fun onEnable() {
        server.scheduler.scheduleAsyncTask(this, ConnectionManager(this))
        logger.info("${TextFormat.DARK_GREEN}Enabled.")
    }

    override fun onDisable() {
        running = false
    }

}