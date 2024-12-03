package com.course.data.mappers

import com.course.data.model.VacancyDto
import com.course.domain.model.Vacancy

fun VacancyDto.toDomainModel(companyName :String = "Unknown"): Vacancy {
    return Vacancy(
        vacancyId = this.vacancyId,
        profession = this.profession.replaceFirstChar { it.uppercase() },
        level = this.level,
        salary = "${this.salary} ₽",
        description = this.description,
        companyName = companyName
    )
}