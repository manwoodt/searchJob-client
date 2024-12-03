package com.course.data.network


import com.course.data.model.CompanyDto
import com.course.data.model.CompanyDetailsDto
import com.course.data.model.VacancyDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("companies")
    suspend fun getCompanies(): List<CompanyDto>

    @GET("companies/{id}")
    suspend fun getCompanyDetails(
        @Path("id") companyId: Int,
    ): CompanyDetailsDto

    @GET("vacancies")
    suspend fun getVacancies(): List<VacancyDto>

    @GET("vacancies/{id}")
    suspend fun getVacancyDetails(
        @Path("id") vacancyId: Int
    ): VacancyDto
}
