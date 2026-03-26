package griffio

import io.ktor.openapi.OpenApiInfo
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.openapi.*
import io.ktor.server.plugins.openapi.*

fun Application.configureRouting() {

    routing {
        openAPI(path = "openapi") {
            info = OpenApiInfo("Http API", "1.0")
            source = OpenApiDocSource.Routing {
                routingRoot.descendants()
            }
        }
        get("/") {
            call.respondText("Hello World!")
        }

        get("/health") {
            call.respondText("Ok")
        }
    }
}
