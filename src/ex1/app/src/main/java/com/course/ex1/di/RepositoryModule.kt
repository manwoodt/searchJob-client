package com.course.ex1.di

import com.course.data.network.ApiService
import com.course.data.repository.CompanyRepositoryImpl
import com.course.domain.repository.CompanyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCompanyRepository(apiService: ApiService): CompanyRepository {
        return CompanyRepositoryImpl(apiService)
    }
}
