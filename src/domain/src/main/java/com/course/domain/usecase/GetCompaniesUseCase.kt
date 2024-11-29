package com.course.domain.usecase

import com.course.domain.model.Company
import com.course.domain.repository.Repository
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): List<Company> {
        return repository.getCompanies()
    }
}