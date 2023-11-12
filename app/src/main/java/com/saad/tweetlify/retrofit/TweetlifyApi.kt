package com.saad.tweetlify.retrofit

import com.saad.tweetlify.models.Fact
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetlifyApi {
    @GET("/v3/b/654227a612a5d37659935889?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<Fact>>

    @GET("/v3/b/654227a612a5d37659935889?meta=false")
    @Headers("X-JSON-Path: facts..category")
    suspend fun getCategory(): Response<Set<String>>
}