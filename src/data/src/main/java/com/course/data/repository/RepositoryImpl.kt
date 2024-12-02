package com.course.data.repository
// тут чуть по-другому
import com.course.data.mappers.toDomainModel
import com.course.data.network.ApiService
import com.course.domain.model.Company
import com.course.domain.model.CompanyDetails
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

    override suspend fun getCompanyDetails(id: Int): CompanyDetails {
        val vacancies = apiService.getVacancies()
        val companyDetails = apiService.getCompanyDetails(id).toDomainModel()
        println("Детали компаний $companyDetails")
        return companyDetails
    }

    override suspend fun getVacancies(): List<Vacancy> {
        val vacanciesDto = apiService.getVacancies()
        return vacanciesDto.map { vacancyDto ->
            vacancyDto.toDomainModel()
        }
    }
    /*
            val vacancies = repository.getVacancies()
        val companies = repository.getCompanies()

        val companyMap = companies.map { it.id to it.name }.toMap()

        return vacancies.map { vacancy ->
            val companyName = companyMap[vacancy.id] ?: "Неизвестная компания"
            vacancy.copy(companyName = companyName)
        }
     */



    override suspend fun getVacancyDetails(id: Int): Vacancy {
        return apiService.getVacancyDetails(id).toDomainModel()
    }

}


