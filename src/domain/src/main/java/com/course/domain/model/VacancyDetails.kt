package com.course.domain.model


data class VacancyDetails(
    val id: Int,
    val profession: String,
    val description: String,
    val salary: String,
    val level: String,
    val companyId: Int,
    val companyName: String
)