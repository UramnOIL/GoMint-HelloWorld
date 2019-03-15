package com.uramnoil.gomint.helloworld.command

import com.uramnoil.gomint.helloworld.HelloWorld

import io.gomint.command.Command
import io.gomint.command.CommandOutput
import io.gomint.command.CommandSender
import io.gomint.command.annotation.*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Name("helloworld")
@Alias("hw")
@Description("Hello, World!と表示します。")
@Permission("helloworld.command.helloworld")
class HelloWorldCommand : Command() {

    override fun execute(commandSender: CommandSender?, alias: String?, arguments: MutableMap<String, Any>?): CommandOutput? {     //きもいので = if (...にしてません。
         return if (commandSender?.hasPermission(permission) == true) {
            GlobalScope.launch {
                async {
                    val helloWorld = HelloWorld()
                    return@async helloWorld.getHelloWorld()
                }.await().let {
                    commandSender.sendMessage(it)
                }
            }

            CommandOutput().success("success")   //よくわからん
        } else {
            CommandOutput().fail("fail")
        }
    }
}