package com.course.ex1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API {
    @GET("companies")
    fun getCompany():Call<List<Company>>
}
val retrofit = Retrofit.Builder()
    .baseUrl("local")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val companyApi:API = retrofit.create(API::class.java)