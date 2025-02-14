package com.quiz.cartoons.di

import com.quiz.cartoons.network.repository.CartoonsRepository
import com.quiz.cartoons.network.repository.CartoonsRepositoryImpl
import com.quiz.cartoons.network.services.ApiServiceImpl
import com.quiz.cartoons.network.services.CartoonService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindCartoonRepository(componentRepositoryImpl: CartoonsRepositoryImpl): CartoonsRepository

    companion object{


        @Provides
        fun providerService():CartoonService{
            return ApiServiceImpl("https://rickandmortyapi.com/api/").create(CartoonService::class.java)
        }
    }
}