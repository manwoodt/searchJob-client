package com.course.ex1

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//class NetworkModule() {
//
//    @Provides
//    @Singleton
//    fun provideOkhttp(): OkHttpClient {
//        return OkHttpClient().newBuilder().build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit { // in this example gson provider is also needed
//        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
//            .baseUrl("baseUrl...")
//            .client(okHttpClient)
//            .build()
//    }
//}

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080"
    private val client = OkHttpClient.Builder().build()

    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }




