package com.course.domain.model


data class Vacancy(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: String,
    val description: String,
    val companyName: String
)