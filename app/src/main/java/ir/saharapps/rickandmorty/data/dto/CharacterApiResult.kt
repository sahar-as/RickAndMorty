package ir.saharapps.rickandmorty.data.dto

data class CharacterApiResult(
    val info: Info,
    val results: List<Result>
)