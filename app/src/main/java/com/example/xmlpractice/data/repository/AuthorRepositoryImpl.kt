package com.example.xmlpractice.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.xmlpractice.data.datasource.AuthorPagingSource
import com.example.xmlpractice.data.datasource.IDataSource
import com.example.xmlpractice.data.datasource.PAGING_SIZE
import com.example.xmlpractice.data.datasource.local.LocalDataSource
import com.example.xmlpractice.data.datasource.remote.RemoteDataSource
import com.example.xmlpractice.data.model.Author
import com.example.xmlpractice.di.UserLocalDatasourceQualifier
import com.example.xmlpractice.di.UserRemoteDatasourceQualifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthorRepositoryImpl @Inject constructor(
    @UserLocalDatasourceQualifier private val localDataSource: IDataSource,
    @UserRemoteDatasourceQualifier private val remoteDataSource: IDataSource
): AuthorRepository {

    override suspend fun getAuthors(): Flow<List<Author>> = flow {
        val operationResult = remoteDataSource.getAuthors()
        emit(operationResult)
    }

    override suspend fun getAuthorsPaging(): Flow<PagingData<Author>> {
        return Pager(
            PagingConfig(PAGING_SIZE),
            pagingSourceFactory = {
                AuthorPagingSource(remoteDataSource)
            }
        ).flow
    }
}