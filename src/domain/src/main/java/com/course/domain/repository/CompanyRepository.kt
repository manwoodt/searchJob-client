package com.course.domain.repository

import com.course.domain.model.Company

interface CompanyRepository {
    suspend fun getCompanies(): List<Company>
}