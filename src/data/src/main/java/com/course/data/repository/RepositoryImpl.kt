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
        // println("Детали компаний $companyDetails")
        return companyDetails
    }

    override suspend fun getVacancies(): List<Vacancy> {

        // Получаем список краткой информации о компаниях
        val companies = apiService.getCompanies()

        // Получаем подробную информацию по каждой компании и добавляем вакансии с именами компаний
        val vacancies = mutableListOf<Vacancy>()
        for (company in companies) {
            val companyDetails = getCompanyDetails(company.companyId) // Запрос подробной информации
            vacancies.addAll(
                companyDetails.vacancies.map { vacancy ->
                    vacancy.copy(companyName = companyDetails.name) // Добавляем имя компании в вакансию
                }
            )
        }
        return vacancies
    }

    override suspend fun getVacancyDetails(id: Int): Vacancy {
        val vacancyWithoutCompanyName = apiService.getVacancyDetails(id).toDomainModel()
        val vacancies = getVacancies()
        if (id != 0)
            vacancyWithoutCompanyName.companyName = vacancies[id - 1].companyName
        return vacancyWithoutCompanyName
    }

}


