package com.course.domain.repository

import com.course.domain.model.Company
import com.course.domain.model.CompanyDetails
import com.course.domain.model.Vacancy

interface Repository {
    suspend fun getCompanies(): List<Company>
    suspend fun getCompanyDetails(id:Int):CompanyDetails

    suspend fun getVacancies(): List<Vacancy>
    suspend fun getVacancyDetails(id:Int):Vacancy
}
