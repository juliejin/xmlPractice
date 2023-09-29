package com.example.xmlpractice.data.datasource.remote

import android.content.Context
import com.example.xmlpractice.data.datasource.IDataSource
import com.example.xmlpractice.data.datasource.operations.FetchAuthorOperation
import com.example.xmlpractice.data.model.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val context: Context): IDataSource {
    override suspend fun getAuthors(): List<Author> {
        val result = FetchAuthorOperation().retrofitCall()
        val operationResult = if (result.isSuccessful) {
           result.body()
        } else {
            listOf()
        }
        return operationResult ?: listOf()
    }

    override suspend fun getAuthorsPaging(beforeId: String?, afterId: String?, size: Int): List<Author> {
        val result = parseData()
        delay(1000)
        if (beforeId == null && afterId == null) return result.subList(0, minOf(size, result.size))
        beforeId?.let { before ->
            for (i in 0..result.size-1) {
                if (result[i].id == before) {
                    //messages before this id
                    val startIndex = maxOf(0, i - 1 - size)
                    return result.subList(startIndex, i)
                }
            }
        }
        afterId?.let { after ->
            for (i in 0..result.size-1) {
                if (result[i].id == after) {
                    //messages before this id
                    return result.subList(i + 1, minOf(i + 1 + size, result.size))
                }
            }
        }
        return listOf()
    }

    fun parseData(): List<Author> {
        var jsonStr = ""
        try {
            jsonStr = context.assets
                .open("author-data.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        var userData = listOf<Author>()
        try {
            val listType = object : TypeToken<List<Author>>(){}.type
            userData = Gson().fromJson(jsonStr, listType)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return userData
    }

}