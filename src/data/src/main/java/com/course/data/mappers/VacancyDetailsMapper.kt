package com.course.data.mappers

import com.course.data.model.VacancyDetailsDto
import com.course.data.model.VacancyDto
import com.course.domain.model.Vacancy
import com.course.domain.model.VacancyDetails

fun VacancyDetailsDto.toDomainModel(): VacancyDetails {
    return VacancyDetails(
        id = this.id,
        profession = this.profession,
        level = this.level,
        salary = "${this.salary} ₽",
        description = this.description,
        companyName = companyName,
        companyId = companyId
    )
}