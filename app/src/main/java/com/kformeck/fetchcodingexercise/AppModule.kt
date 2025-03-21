package com.kformeck.fetchcodingexercise

import com.kformeck.fetchcodingexercise.ui.LoadingManager
import com.kformeck.fetchcodingexercise.ui.navigation.RouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoadingManager() = LoadingManager()

    @Provides
    @Singleton
    fun provideRouteNavigator() = RouteNavigator()
}
