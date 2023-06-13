package ir.saharapps.rickandmorty.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rick_morty")
data class RicKAndMorty(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val episodeList: List<String>
)
