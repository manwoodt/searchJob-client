package com.course.domain.model


data class CompanyDetails(
    val id:Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<Vacancy>,
    val contacts: String
)