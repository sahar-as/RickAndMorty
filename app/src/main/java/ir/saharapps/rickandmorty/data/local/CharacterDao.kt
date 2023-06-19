package ir.saharapps.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: CharacterEntity)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM  character WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity

    @Query("UPDATE character SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteState(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM character WHERE is_favorite = 1")
    suspend fun getAllFavoriteCharacter(): List<CharacterEntity>
}