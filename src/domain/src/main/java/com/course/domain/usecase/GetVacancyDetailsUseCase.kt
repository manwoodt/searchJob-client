package com.course.domain.usecase

import com.course.domain.model.Vacancy
import com.course.domain.model.VacancyDetails
import com.course.domain.repository.Repository
import javax.inject.Inject

class GetVacancyDetailsUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(vacancyId:Int): Vacancy {
    return repository.getVacancyDetails(vacancyId)
    }

}