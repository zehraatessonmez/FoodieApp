package com.zehraatessonmez.foodieapp.di

import com.zehraatessonmez.foodieapp.data.datasource.YemeklerDataSource
import com.zehraatessonmez.foodieapp.data.repo.YemeklerRepository
import com.zehraatessonmez.foodieapp.retrofit.ApiUtils
import com.zehraatessonmez.foodieapp.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Provides
    @Singleton
    fun provideYemeklerRepository(yds:YemeklerDataSource) : YemeklerRepository {
        return YemeklerRepository(yds)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao:YemeklerDao) : YemeklerDataSource {
        return YemeklerDataSource(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }

}