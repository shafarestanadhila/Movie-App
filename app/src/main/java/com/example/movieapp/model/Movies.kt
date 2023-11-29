package com.example.movieapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val id: String,
    val name: String,
    val photoUrl: String,
    val description: String,
) : Parcelable