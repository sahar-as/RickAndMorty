package ir.saharapps.rickandmorty.data.dto

import androidx.annotation.Keep

@Keep
data class CharacterApiResult(
    val info: Info,
    val results: List<Result>
)