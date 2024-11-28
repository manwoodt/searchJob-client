package com.course.data.model

import kotlinx.serialization.Serializable


data class CompanyDto(
    val id:Int,
    val name: String,
    val fieldOfActivity: String
)

