package com.course.ex1

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("companies")
   suspend fun getCompany():List<Company>
}
