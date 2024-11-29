package com.course.data.mappers


import com.course.data.model.CompanyInfoDto
import com.course.domain.model.CompanyInfo

fun CompanyInfoDto.toDomainModel(): CompanyInfo {
    return CompanyInfo(
        id = this.id,
        name = this.name,
        fieldOfActivity = this.fieldOfActivity,
        vacancies = this.vacancies.map { it.toDomainModel("Unknown") },
        contacts = this.contacts
    )
}