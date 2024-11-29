package com.course.domain.usecase

import com.course.domain.model.CompanyInfo
import com.course.domain.repository.Repository
import javax.inject.Inject

class GetCompanyDetailsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(companyId: Int): CompanyInfo {
        return repository.getCompanyDetails(companyId)
    }
}