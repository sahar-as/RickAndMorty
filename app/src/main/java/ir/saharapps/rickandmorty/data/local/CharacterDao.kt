package ir.saharapps.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.saharapps.rickandmorty.data.dto.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: Character)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<Character>

    @Query("SELECT * FROM  character WHERE id = :id")
    suspend fun getCharacterById(id: Int): Character
}