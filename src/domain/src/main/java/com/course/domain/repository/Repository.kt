package com.course.domain.repository

import com.course.domain.model.Company
import com.course.domain.model.CompanyInfo
import com.course.domain.model.Vacancy

interface Repository {
    suspend fun getCompanies(): List<Company>

    suspend fun getCompanyDetails(id:Int):CompanyInfo
    suspend fun getVacancies(): List<Vacancy>
}