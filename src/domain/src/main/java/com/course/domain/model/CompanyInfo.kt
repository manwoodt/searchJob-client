package com.course.domain.model


data class CompanyInfo(
    val id:Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<Vacancy>,
    val contacts: String
)