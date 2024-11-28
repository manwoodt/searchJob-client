package com.course.domain.repository

import com.course.domain.model.Company
import com.course.domain.model.CompanyInfo

interface CompanyRepository {
    suspend fun getCompanies(): List<Company>

    suspend fun getCompanyDetails(id:Int):CompanyInfo
}
