package com.example.xmlpractice.data.datasource.local

import com.example.xmlpractice.data.datasource.IDataSource
import com.example.xmlpractice.data.model.Author
import javax.inject.Inject

class LocalDataSource @Inject constructor(): IDataSource {
    override suspend fun getAuthors(): List<Author> {
        TODO("Not yet implemented")
    }

    override suspend fun getAuthorsPaging(
        beforeId: String?,
        afterId: String?,
        size: Int
    ): List<Author> {
        TODO("Not yet implemented")
    }

}