package com.course.data.repository
// тут чуть по-другому
import com.course.data.network.ApiService
import com.course.domain.model.Company
import com.course.domain.repository.CompanyRepository

class CompanyRepositoryImpl(
    private val apiService: ApiService,
) : CompanyRepository {

    override suspend fun getCompanies(): List<Company> {
        return apiService.getCompanies()
            .map { dto ->
            Company (
                id = dto.id,
                name = dto.name,
                fieldOfActivity = dto.fieldOfActivity,
                contacts = dto.contacts,
               // vacancies = dto.vacancies
            )
        }
    }

}


/*
class CompanyRepositoryImpl{

    private val apiService = retrofit.create(com.course.data.network.ApiService::class.java)

    suspend fun getCompanies(): List<CompanyDto> {
        return apiService.getCompany()
    }

}
 */