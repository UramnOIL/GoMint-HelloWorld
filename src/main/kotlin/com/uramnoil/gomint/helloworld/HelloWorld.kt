package com.uramnoil.gomint.helloworld

import io.gomint.config.InvalidConfigurationException
import io.gomint.event.EventListener
import io.gomint.event.player.PlayerJoinEvent
import io.gomint.plugin.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

@PluginName("HelloWorld")
@Version(major = 1, minor = 0)
@Startup(StartupPriority.LOAD)
class HelloWorld : Plugin(), EventListener {
    override fun onStartup() {
        logger.info( "たぶんonLoad" )
    }

    /**
     * @throws InvalidConfigurationException
     */
    override fun onInstall() {
        logger.info( "たぶんonLoad" )
    }

    fun onJoin(event: PlayerJoinEvent) = GlobalScope.launch {
        val client = OkHttpClient()
        val request = Request.Builder().url("http://weather.livedoor.com/forecast/webservice/json/v1?city=140010").build()  //横浜市の天気！！！一度はおいで！！！！
        async {
            val response = client.newCall(request).execute()
            return@async response.body()?.toString()
        }.await().let {
            event.player.sendMessage(it)
        }
    }
}