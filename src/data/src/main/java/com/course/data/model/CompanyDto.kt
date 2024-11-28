package com.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDto(
    val id:Int,
    val name: String,
    val fieldOfActivity: String,
 //   val vacancies: List<VacancyDto>,
    val contacts: String
)

@Serializable
data class VacancyDto(
    val id: Int,
    val profession: String,
    val level: String,
    val salary: Int,
    val description: String
)