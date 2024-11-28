package com.course.domain.usecase

import com.course.domain.model.Company
import com.course.domain.model.CompanyInfo
import com.course.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompanyDetailsUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    suspend operator fun invoke(companyId: Int): CompanyInfo {
        return companyRepository.getCompanyDetails(companyId)
    }
}