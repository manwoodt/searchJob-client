package com.course.data.network


import com.course.data.model.CompanyDto
import retrofit2.http.GET

interface ApiService {
    @GET("companies")
   suspend fun getCompanies():List<CompanyDto>
}
