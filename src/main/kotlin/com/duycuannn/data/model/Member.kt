package com.duycuannn.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val modifyDate: String
)