package com.course.data.di

import com.course.data.network.ApiService
import com.course.data.repository.RepositoryImpl
import com.course.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCompanyRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }
}
