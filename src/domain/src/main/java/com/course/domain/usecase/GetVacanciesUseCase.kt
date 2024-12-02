package com.course.domain.usecase

import com.course.domain.model.Vacancy
import com.course.domain.repository.Repository
import javax.inject.Inject

class GetVacanciesUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(): List<Vacancy> {
//        val vacancies = repository.getVacancies()
//        val companies = repository.getCompanies()
//
//        val companyMap = companies.map { it.id to it.name }.toMap()
//
//        return vacancies.map { vacancy ->
//            val companyName = companyMap[vacancy.id] ?: "Неизвестная компания"
//            vacancy.copy(companyName = companyName)
//        }
        return repository.getVacancies()


    }
}