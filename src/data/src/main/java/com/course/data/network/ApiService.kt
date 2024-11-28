package com.course.data.network


import com.course.data.model.CompanyDto
import com.course.data.model.CompanyInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("companies")
   suspend fun getCompanies():List<CompanyDto>

   @GET("companies/{id}")
   suspend fun getCompanyDetails(
       @Path("id") companyId:Int): CompanyInfoDto
}
