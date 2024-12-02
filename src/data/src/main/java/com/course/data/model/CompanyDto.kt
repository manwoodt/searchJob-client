package com.course.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyDto(
    @SerializedName("id")  val companyId:Int,
    val name: String,
    val fieldOfActivity: String
)

