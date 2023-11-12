package com.saad.tweetlify.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saad.tweetlify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val categories: StateFlow<Set<String>>
        get() = repository.categories

    init {
        viewModelScope.launch {
            repository.getCategory()
        }
    }
}