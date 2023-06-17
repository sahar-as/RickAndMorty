package ir.saharapps.rickandmorty.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CharacterApiResult(
    val info: Info,
    @SerializedName("results")
    val character: List<CharacterDto>
)