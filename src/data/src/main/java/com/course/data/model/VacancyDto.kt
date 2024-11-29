package com.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VacancyDto(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val description: String,
    val companyId: Int
)