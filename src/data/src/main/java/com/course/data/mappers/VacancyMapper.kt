package com.course.data.mappers

import com.course.data.model.VacancyDto
import com.course.domain.model.Vacancy

fun VacancyDto.toDomainModel(): Vacancy {
    return Vacancy(
        id = this.id,
        profession = this.profession,
        level = this.level,
        salary = this.salary,
        description = this.description

    )
}