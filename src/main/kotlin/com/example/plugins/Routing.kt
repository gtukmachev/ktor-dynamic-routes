package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    println("> configureRouting")
    routing {
        println("   > routing")
        println("   . before one")
        get("/one") {
            println("       > one")
            call.respondText("Hello One!")
            println("       < one")
        }
        println("   . before two")
        get("/two") {
            println("       > two")
            call.respondText("Hello Two!")
            println("       < two")
        }
        println("   < routing")
    }
    println("< configureRouting")
}
