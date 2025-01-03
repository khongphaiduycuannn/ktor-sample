package com.duycuannn.data.source

import ch.qos.logback.core.util.Loader.getResource
import com.duycuannn.constants.APIConstants
import com.duycuannn.data.model.Member
import java.io.File
import java.util.*

class MemberDataSource {

    companion object {

        private var INSTANCE: MemberDataSource? = null

        fun getInstance(): MemberDataSource {
            return INSTANCE ?: synchronized(this) {
                MemberDataSource()
            }
        }
    }


    private val imagePath = "${APIConstants.BASE_URL}${APIConstants.EndPoint.IMAGE_RESOURCES_ROUTE}"

    private val modifyDate = Date().time.toString()
    private val members = mutableListOf(
        Member(1, "Nguyễn Duy Minh Quân", "$imagePath/1.jpg", modifyDate),
        Member(2, "Trần Thị Thương", "$imagePath/2.jpg", modifyDate),
        Member(3, "Đỗ Danh Nghĩa", "$imagePath/3.jpg", modifyDate),
        Member(4, "Nguyễn Thị Diệu Linh", "$imagePath/4.jpg", modifyDate),
        Member(5, "Nguyễn Khắc Huy", "$imagePath/5.jpg", modifyDate),
        Member(6, "Nguyễn Hương Giang", "$imagePath/6.jpg", modifyDate)
    )


    fun getAllMembers(): List<Member> {
        return members
    }

    fun addMember(member: Member) {
        if (members.find { it == member } != null) {
            throw IllegalStateException("Cannot duplicate members!")
        }
        members.add(member)
    }

    fun updateMember(member: Member) {
        val index = members.indexOfFirst { it.id == member.id }
        if (index == -1) {
            throw IllegalStateException("Member not found!")
        }
        members[index] = member
    }

    fun deleteMember(id: Int): Boolean {
        return members.removeIf { it.id == id }
    }


    fun getImageUrls(): List<String> {
        val imageUrls = mutableListOf<String>()

        getResource("static/images", this::class.java.classLoader)?.let { resource ->
            val imagesDir = File(resource.toURI())

            if (!imagesDir.exists() || !imagesDir.isDirectory) {
                throw IllegalStateException("Images directory not found!")
            } else {
                val imageFiles = imagesDir.listFiles()?.filter { it.isFile }?.map { it.name } ?: emptyList()
                imageUrls.addAll(imageFiles)
            }
        }

        return imageUrls
    }
}