package com.course.domain.model


data class Vacancy(
    val vacancyId: Int,
    val profession: String,
    val level: String,
    val salary: String,
    val description: String,
    var companyName: String
)