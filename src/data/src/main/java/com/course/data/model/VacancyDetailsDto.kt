package com.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VacancyDetailsDto(
    val id: Int,
    val profession: String,
    val description: String,
    val salary: String,
    val level: String,
    val companyId: Int,
    val companyName: String
)