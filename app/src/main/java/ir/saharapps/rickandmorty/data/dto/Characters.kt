package ir.saharapps.rickandmorty.data.dto

import androidx.annotation.Keep

@Keep
data class Characters(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)