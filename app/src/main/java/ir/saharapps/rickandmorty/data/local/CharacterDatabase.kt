package ir.saharapps.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.saharapps.rickandmorty.data.dto.Character

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}