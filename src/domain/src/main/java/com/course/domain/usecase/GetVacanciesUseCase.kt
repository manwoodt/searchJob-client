package com.course.domain.usecase

import com.course.domain.model.Vacancy
import com.course.domain.repository.Repository
import javax.inject.Inject

class GetVacanciesUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(): List<Vacancy> {
       return repository.getVacancies()

    }
}