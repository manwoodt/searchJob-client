package com.course.data.network


import com.course.data.model.CompanyDto
import com.course.data.model.CompanyInfoDto
import retrofit2.http.GET

interface ApiService {
    @GET("companies")
   suspend fun getCompanies():List<CompanyDto>

   @GET("companies/details")
   suspend fun getCompanyDescription(id:Int): CompanyInfoDto
}
