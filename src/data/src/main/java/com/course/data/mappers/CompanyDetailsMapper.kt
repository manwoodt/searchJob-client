package com.course.data.mappers


import com.course.data.model.CompanyInfoDto
import com.course.domain.model.CompanyDetails

fun CompanyInfoDto.toDomainModel(): CompanyDetails {
    return CompanyDetails(
        companyId =  this.companyId,
        name = this.name,
        fieldOfActivity = this.fieldOfActivity,
        vacancies = this.vacancies.map { it.toDomainModel(this.name) },
        contacts = this.contacts
    )
}