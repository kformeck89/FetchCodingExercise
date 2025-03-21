package com.kformeck.fetchcodingexercise.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FetchApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideFetchService(
        retrofit: Retrofit,
    ): FetchService = retrofit.create(FetchService::class.java)

    @Provides
    @Singleton
    fun provideFetchRepository(
        fetchService: FetchService,
    ): FetchRepository = FetchRepository(fetchService)
}
