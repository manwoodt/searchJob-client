package com.course.domain.usecase

import com.course.domain.model.Company
import com.course.domain.repository.CompanyRepository

class GetCompanyUseCase(
    private val companyRepository: CompanyRepository
) {

    suspend fun execute(): List<Company> {
        return companyRepository.getCompanies()
    }
}