package com.example.movieapp.ui.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.MoviesRepository
import com.example.movieapp.model.Movies

class MainViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _searchResults = mutableStateOf<List<Movies>>(emptyList())
    val searchResults: State<List<Movies>> get() = _searchResults

    fun search(newQuery: String) {
        _query.value = newQuery
        _searchResults.value = repository.searchMovies(_query.value)
            .sortedBy { it.name }
    }
}

class ViewModelFactory(private val repository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}