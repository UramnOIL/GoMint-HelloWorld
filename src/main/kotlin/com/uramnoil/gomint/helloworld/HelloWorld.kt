package com.uramnoil.gomint.helloworld

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
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
    companion object {
        const val WH_URL_BASE   = "http://weather.livedoor.com/forecast/webservice/json/v1?city=140010"
        const val WH_URL_PARAMS = "city=14410"
    }

    override fun onStartup() {
        logger.info( "たぶんonLoad" )
    }

    /**
     * @throws InvalidConfigurationException
     */
    override fun onInstall() {
        logger.info( "たぶんonEnable" )
        pluginManager.registerListener(this, this)
    }

    fun onJoin(event: PlayerJoinEvent) = GlobalScope.launch {
        async {
            return@async getWeather()
        }.await().let {
            event.player.sendMessage(it)
        }
    }

    fun getWeather(): String {
        val client = OkHttpClient()
        val request = Request.Builder().url(WH_URL_BASE + "?" + WH_URL_PARAMS).build()  //横浜市の天気！！！一度はおいで！！！！
        val response = client.newCall(request).execute()
        val body = response.body().toString()
        val json = Json.parse(body).asObject()
        return json["description"].asObject()["text"].toString()
    }
}