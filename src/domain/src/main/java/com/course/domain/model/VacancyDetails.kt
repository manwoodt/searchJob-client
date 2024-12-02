package com.course.domain.model


data class VacancyDetails(
    val vacancyId: Int,
    val profession: String,
    val description: String,
    val salary: String,
    val level: String,
    val companyName: String
)