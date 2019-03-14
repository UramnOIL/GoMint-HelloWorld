package com.uramnoil.gomint.helloworld

import org.junit.Test

class HelloWorldTest {
    @Test
    fun getHelloWorldTest() {
        val helloWorld = HelloWorld()
        assert(helloWorld.getHelloWorld() == "Hello, World!")
    }
}