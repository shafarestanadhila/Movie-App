package com.example.movieapp.data

import com.example.movieapp.model.Movies
import com.example.movieapp.model.MoviesData

class MoviesRepository {
    fun searchMovies(query: String): List<Movies> {
        return MoviesData.movies.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}