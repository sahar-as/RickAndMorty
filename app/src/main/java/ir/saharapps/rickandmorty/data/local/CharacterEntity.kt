package ir.saharapps.rickandmorty.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.saharapps.rickandmorty.data.dto.Location
import ir.saharapps.rickandmorty.data.dto.Origin


@Entity(tableName = "character")
data class CharacterEntity (
    @PrimaryKey
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    @ColumnInfo("location_name")
    val locationName: String,
    @ColumnInfo("location_url")
    val locationUrl: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)