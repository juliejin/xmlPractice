package com.example.xmlpractice.data.datasource

import com.example.xmlpractice.data.model.Author

interface IDataSource {
    suspend fun getAuthors(): List<Author>

    suspend fun getAuthorsPaging(beforeId: String?, afterId: String?, size: Int): List<Author>
}