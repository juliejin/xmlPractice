package com.example.xmlpractice.di

import android.content.Context
import com.example.xmlpractice.data.datasource.IDataSource
import com.example.xmlpractice.data.datasource.local.LocalDataSource
import com.example.xmlpractice.data.datasource.remote.RemoteDataSource
import com.example.xmlpractice.data.repository.AuthorRepository
import com.example.xmlpractice.data.repository.AuthorRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class UserRemoteDatasourceQualifier

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class UserLocalDatasourceQualifier

@InstallIn(SingletonComponent::class)
@Module
abstract class UserModule {

    @Binds
    abstract fun bindsRepository(repository: AuthorRepositoryImpl): AuthorRepository

    @Binds
    @UserRemoteDatasourceQualifier
    abstract fun bindsRemoteDataSource(dataSource: RemoteDataSource): IDataSource

    @Binds
    @UserLocalDatasourceQualifier
    abstract fun bindsLocalDataSource(dataSource: LocalDataSource): IDataSource
}