package com.course.domain.model


data class CompanyDetails(
    val companyId:Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<Vacancy>,
    val contacts: String
)