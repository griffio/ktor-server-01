package griffio

import guru.zoroark.tegral.openapi.dsl.schema
import guru.zoroark.tegral.openapi.ktor.describe
import guru.zoroark.tegral.openapi.ktor.openApiEndpoint
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {

        get("/") {
            call.respondText("Hello World!")
        } describe {
            description = "Returns a plain text greeting"
            200 response {
                description = "greeting response"
                plainText { schema("Hello World!") }
            }
        }

        get("/health") {
            call.respondText("Ok")
        } describe {
            description = "Returns a plain text health check"
            200 response {
                description = "health response"
                plainText { schema("Ok") }
            }
        }

        openApiEndpoint("/openapi")
    }
}
