package com.example.xmlpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.xmlpractice.data.model.Author
import com.example.xmlpractice.data.repository.AuthorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject constructor(private val repository: AuthorRepository): ViewModel() {

    private val _authorLiveData = MutableStateFlow<List<Author>>(listOf())
    val authorLiveData: StateFlow<List<Author>> get() = _authorLiveData

    private val _authorPagingData = MutableStateFlow<PagingData<Author>>(PagingData.empty())
    val authorPagingData: StateFlow<PagingData<Author>> get() = _authorPagingData

    fun fetchAuthorList() {
        viewModelScope.launch {
            repository.getAuthors().collect {
                _authorLiveData.value = it
            }
        }
    }

    fun fetchAuthorListPagination() {
        viewModelScope.launch {
            repository.getAuthorsPaging().cachedIn(viewModelScope).collect {
                _authorPagingData.value = it
            }
        }
    }
}