package ir.saharapps.rickandmorty.domain.model

data class DetailCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<String>
)
