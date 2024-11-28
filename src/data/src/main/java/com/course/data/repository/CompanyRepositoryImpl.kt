package com.course.data.repository
// тут чуть по-другому
import com.course.data.mappers.toDomainModel
import com.course.data.network.ApiService
import com.course.domain.model.Company
import com.course.domain.model.CompanyInfo
import com.course.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : CompanyRepository {

    override suspend fun getCompanies(): List<Company> {
        return apiService.getCompanies().map { it.toDomainModel() }
        }

    override suspend fun getCompanyDetails(id: Int): CompanyInfo {
        return apiService.getCompanyDetails(id).toDomainModel()
    }

    }


