package com.saad.tweetlify.repository

import com.saad.tweetlify.models.Fact
import com.saad.tweetlify.retrofit.TweetlifyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class Repository @Inject constructor(private val tweetlifyApi: TweetlifyApi) {

    private val _categories = MutableStateFlow<Set<String>>(emptySet())
    val categories: StateFlow<Set<String>>
        get() = _categories

    private val _tweetList = MutableStateFlow<List<Fact>>(emptyList())
    val tweetList: StateFlow<List<Fact>>
        get() = _tweetList

    suspend fun getCategory() {
        val response = tweetlifyApi.getCategory()
        if (response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetlifyApi.getTweets("facts[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body() != null) {
            _tweetList.emit(response.body()!!)
        }
    }


}