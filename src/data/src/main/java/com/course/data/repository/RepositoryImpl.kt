package com.course.data.repository
// тут чуть по-другому
import com.course.data.mappers.toDomainModel
import com.course.data.network.ApiService
import com.course.domain.model.Company
import com.course.domain.model.CompanyInfo
import com.course.domain.model.Vacancy
import com.course.domain.model.VacancyDetails
import com.course.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : Repository {

    override suspend fun getCompanies(): List<Company> {
        return apiService.getCompanies().map { it.toDomainModel() }
    }

    override suspend fun getCompanyDetails(id: Int): CompanyInfo {
        return apiService.getCompanyDetails(id).toDomainModel()
    }

    override suspend fun getVacancies(): List<Vacancy> {
        val vacanciesDto = apiService.getVacancies()

        return vacanciesDto.map { vacancyDto ->
            vacancyDto.toDomainModel("Unknown")
        }
    }

    override suspend fun getVacancyDetails(id: Int): VacancyDetails {
        return apiService.getVacancyDetails(id).toDomainModel()
    }

}


