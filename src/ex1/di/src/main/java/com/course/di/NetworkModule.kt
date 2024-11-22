package com.course.di

import com.course.data.network.ApiService
import com.course.data.repository.CompanyRepositoryImpl
import com.course.domain.repository.CompanyRepository
import com.course.domain.usecase.GetCompanyUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/*
@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit { // in this example gson provider is also needed
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("baseUrl...")
            .client(okHttpClient)
            .build()
    }
}
 */


object DependencyProvider {

    private const val BASE_URL = "http://10.0.2.2:8080"

    // Создание Retrofit-клиента
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    // API
    private val apiService: ApiService =
        retrofit.create(ApiService::class.java)


    // Repository
    private val companyRepository: CompanyRepository by lazy {
        CompanyRepositoryImpl(apiService)
    }

    // UseCase
    val getCompanyUseCase: GetCompanyUseCase by lazy {
        GetCompanyUseCase(companyRepository)
    }
}

//object RetrofitClient {
//
//    private const val BASE_URL = "http://10.0.2.2:8080"
//    private val client = OkHttpClient.Builder().build()
//
//    val retrofit: Retrofit =
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//
//    }




