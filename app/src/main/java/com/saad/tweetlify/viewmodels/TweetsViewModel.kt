package com.saad.tweetlify.viewmodels


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.tweetlify.models.Fact
import com.saad.tweetlify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val tweetList: StateFlow<List<Fact>>
        get() = repository.tweetList

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            repository.getTweets(category!!)
        }
    }
}