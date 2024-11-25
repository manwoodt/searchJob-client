package com.course.domain.usecase

import com.course.domain.model.Company
import com.course.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    suspend operator fun invoke(): List<Company> {
        return companyRepository.getCompanies()
    }
}