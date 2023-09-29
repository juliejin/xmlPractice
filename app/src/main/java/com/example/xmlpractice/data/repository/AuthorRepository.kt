package com.example.xmlpractice.data.repository

import androidx.paging.PagingData
import com.example.xmlpractice.data.model.Author
import kotlinx.coroutines.flow.Flow

interface AuthorRepository {
    suspend fun getAuthors(): Flow<List<Author>>

    suspend fun getAuthorsPaging(): Flow<PagingData<Author>>
}