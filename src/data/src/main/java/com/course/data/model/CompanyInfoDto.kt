package com.course.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CompanyInfoDto(
    val id:Int,
    val name: String,
    val fieldOfActivity: String,
    val vacancies: List<VacancyDto>,
    val contacts: String
)