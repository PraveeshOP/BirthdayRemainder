package com.example.prkha2082.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Update
    suspend fun update(person: Person)

    @Query("SELECT * FROM person_table ORDER BY id DESC")
    fun getAllPersons(): Flow<List<Person>>

    @Query("SELECT * FROM person_table")
    fun getAllPersonsOnce(): List<Person>
}
