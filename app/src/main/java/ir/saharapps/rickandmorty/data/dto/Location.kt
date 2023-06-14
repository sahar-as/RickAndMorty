package ir.saharapps.rickandmorty.data.dto

import androidx.annotation.Keep

@Keep
data class Location(
    val name: String,
    val url: String
)