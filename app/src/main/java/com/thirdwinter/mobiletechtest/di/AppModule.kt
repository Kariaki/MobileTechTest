package com.thirdwinter.mobiletechtest.di

import com.thirdwinter.mobiletechtest.data.repository.GetNewsArticleRepo
import com.thirdwinter.mobiletechtest.data.repository.repository_implemetation.GetNewsArticleRepoImpl
import com.thirdwinter.mobiletechtest.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(
            GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideGetNewsRepo(apiService: ApiService): GetNewsArticleRepo {
        return GetNewsArticleRepoImpl(apiService = apiService)
    }

}