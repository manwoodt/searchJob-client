package com.course.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyDto(
    @SerializedName("id")   val vacancyId: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val description: String
)