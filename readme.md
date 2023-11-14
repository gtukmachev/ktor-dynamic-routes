### Example of Ktor Routing

#### Conclusion:
Ktor run the code in `routing { ... }` section only once to build routes configuration. 

The all the `bodies` of routes will be executed independently. 


#### Example:
routing:

```kotlin
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
```

Output on the application run:
```
2023-11-14 16:39:22.542 [main] INFO  ktor.application - Autoreload is disabled because the development mode is off.
> configureRouting
   > routing
   . before one
   . before two
   < routing
< configureRouting
2023-11-14 16:39:22.760 [main] INFO  ktor.application - Application started in 0.241 seconds.
2023-11-14 16:39:22.870 [DefaultDispatcher-worker-1] INFO  ktor.application - Responding at http://0.0.0.0:8080
```

Output on the `/one` request:
```
2023-11-14 16:40:16.758 [eventLoopGroupProxy-4-1] TRACE io.ktor.routing.Routing - Trace for [one]
/, segment:0 -> SUCCESS @ /
  /one, segment:1 -> SUCCESS @ /one
    /one/(method:GET), segment:1 -> SUCCESS @ /one/(method:GET)
  /two, segment:0 -> FAILURE "Selector didn't match" @ /two
Matched routes:
  "" -> "one" -> "(method:GET)"
Route resolve result:
  SUCCESS @ /one/(method:GET)
       > one
       < one
```

Output on the `/two` request:
```
2023-11-14 16:40:54.388 [eventLoopGroupProxy-4-1] TRACE io.ktor.routing.Routing - Trace for [two]
/, segment:0 -> SUCCESS @ /
  /one, segment:0 -> FAILURE "Selector didn't match" @ /one
  /two, segment:1 -> SUCCESS @ /two
    /two/(method:GET), segment:1 -> SUCCESS @ /two/(method:GET)
Matched routes:
  "" -> "two" -> "(method:GET)"
Route resolve result:
  SUCCESS @ /two/(method:GET)
       > two
       < two
```

