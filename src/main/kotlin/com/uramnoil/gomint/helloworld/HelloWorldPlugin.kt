package com.uramnoil.gomint.helloworld

import io.gomint.config.InvalidConfigurationException
import io.gomint.event.EventHandler
import io.gomint.event.EventListener
import io.gomint.event.player.PlayerJoinEvent
import io.gomint.plugin.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@PluginName("HelloWorld")    //プラグインの名前
@Version(major = 1, minor = 0)      //バージョン
@Startup(StartupPriority.LOAD)      //ロードのタイミング
class HelloWorldPlugin: Plugin(), EventListener {
    companion object {
        const val WH_URL_BASE   = "http://weather.livedoor.com/forecast/webservice/json/v1?city=140010"
        const val WH_URL_PARAMS = "city=14410"
    }

    private val helloWorld = HelloWorld()

    override fun onStartup() = logger.info( "たぶんonLoad" )

    /**
     * @throws InvalidConfigurationException
     */
    override fun onInstall() {
        logger.info( "たぶんonEnable" )
        pluginManager.registerListener(this, this)  //イベントリスナの登録
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) = GlobalScope.launch {
        async {
            return@async helloWorld.getHelloWorld()
        }.await().let {
            event.player.sendMessage(it)    //Hello, World!
        }
    }
}