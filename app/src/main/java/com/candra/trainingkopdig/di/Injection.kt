package com.candra.trainingkopdig.di

import android.content.Context
import com.candra.trainingkopdig.helper.JsonHelper
import com.candra.trainingkopdig.model.data.RemoteDataSource
import com.candra.trainingkopdig.repository.CoffeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Injection
{
    @Singleton
    @Provides
    fun provideJsonHelper(@ApplicationContext context: Context) = JsonHelper(context)

    @Singleton
    @Provides
    fun providesRemoteDataSource(jsonHelper: JsonHelper): RemoteDataSource = RemoteDataSource(jsonHelper)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): CoffeeRepository = CoffeeRepository(remoteDataSource)
}