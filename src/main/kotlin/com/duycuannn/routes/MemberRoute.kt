package com.duycuannn.routes

import com.duycuannn.constants.APIConstants
import com.duycuannn.data.model.Member
import com.duycuannn.data.source.MemberDataSource
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


private val memberDataSource = MemberDataSource.getInstance()


fun Route.getAllMembers() {
    get(APIConstants.EndPoint.GET_ALL_MEMBERS) {
        call.respond<List<Member>>(
            HttpStatusCode.OK,
            memberDataSource.getAllMembers()
        )
    }
}

fun Route.createMember() {
    post(APIConstants.EndPoint.CREATE_MEMBER) {
        try {
            val member = call.receive<Member>()
            memberDataSource.addMember(member)

            call.respond(HttpStatusCode.Created)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}

fun Route.updateMember() {
    put(APIConstants.EndPoint.UPDATE_MEMBER) {
        try {
            val member = call.receive<Member>()
            memberDataSource.updateMember(member)

            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteMember() {
    delete(APIConstants.EndPoint.DELETE_MEMBERS + "/{memberId}") {
        val id = try {
            call.parameters["memberId"]?.toInt()
        } catch (_: Exception) {
            null
        }

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }

        if (memberDataSource.deleteMember(id)) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.getImageUrls() {
    get(APIConstants.EndPoint.GET_IMAGES) {
        call.respond<List<String>>(
            HttpStatusCode.OK,
            memberDataSource.getImageUrls()
        )
    }
}