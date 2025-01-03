package com.duycuannn

import com.duycuannn.configs.configureMonitoring
import com.duycuannn.configs.configureRouting
import com.duycuannn.configs.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
