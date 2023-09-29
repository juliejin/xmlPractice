package com.example.xmlpractice.data.datasource.operations

import com.example.xmlpractice.data.model.Author
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

/*
https://run.mocky.io/v3/fce3893d-6840-4845-8ba6-b7c2a58b6426
* */
interface FetchAuthorInterface {
    @GET("v3/fce3893d-6840-4845-8ba6-b7c2a58b6426")
    suspend fun getAuthor(): Response<List<Author>>

    //the body can be a json string containing beforeid & afterid
    @GET("v3/fce3893d-6840-4845-8ba6-b7c2a58b6426")
    suspend fun getAuthorPaging(@Body body: String)
}