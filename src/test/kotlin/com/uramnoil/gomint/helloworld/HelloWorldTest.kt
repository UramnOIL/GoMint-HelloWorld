package com.uramnoil.gomint.helloworld

import org.junit.Test

class HelloWorldTest {
    @Test
    fun getHelloWorldTest() {
        val helloWorld = HelloWorld()
        print(helloWorld.getHelloWorld())
        assert(helloWorld.getHelloWorld() == "Hello, World!")
    }
}