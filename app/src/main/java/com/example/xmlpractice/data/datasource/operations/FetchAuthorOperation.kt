package com.example.xmlpractice.data.datasource.operations

import com.example.xmlpractice.data.model.Author
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FetchAuthorOperation {
    suspend fun retrofitCall(): Response<List<Author>> {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .client(okhttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(FetchAuthorInterface::class.java)
        return service.getAuthor()
    }
}