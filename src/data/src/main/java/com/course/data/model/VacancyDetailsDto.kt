package com.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VacancyDetailsDto(
    val vacancyId: Int,
    val profession: String,
    val description: String,
    val salary: String,
    val level: String,
    val companyName: String
)