package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*
import kotlin.concurrent.schedule

var i = 1

fun Application.configureRouting() {
    println("> configureRouting")
    routing {
        println("   > routing")
        get("/one") {
            println("       > one")
            call.respondText("Hello One!")
            println("       < one")
        }

        Timer().schedule(2000, 2000) {
            val n = ++i
            println("new route: /$n")
            get("/$n") { call.respondText("Hello $n!") }
        }
        println("   < routing")
    }
    println("< configureRouting")
}
