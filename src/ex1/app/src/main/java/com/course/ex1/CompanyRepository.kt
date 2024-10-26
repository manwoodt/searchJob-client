package com.course.ex1

import com.course.ex1.RetrofitClient.retrofit

class CompanyRepository{

    private val apiService = retrofit.create(API::class.java)

    suspend fun getCompany(): List<Company> {
        return apiService.getCompany()
    }

}