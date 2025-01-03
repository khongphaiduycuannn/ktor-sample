package com.duycuannn.configs

import com.duycuannn.constants.APIConstants
import com.duycuannn.routes.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {

        /* Member route */
        route(APIConstants.EndPoint.MEMBER_ROUTE) {
            getAllMembers()
            createMember()
            updateMember()
            deleteMember()

            getImageUrls()
        }

        /* Resources */
        staticResources("/resources", "static")
    }
}
