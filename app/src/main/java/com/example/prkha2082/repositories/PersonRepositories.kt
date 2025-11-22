package com.example.prkha2082.repositories

import com.example.prkha2082.data.Person
import com.example.prkha2082.data.PersonDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class PersonRepository(private val dao: PersonDao) {
    val allPersons: Flow<List<Person>> = dao.getAllPersons()

    fun getAllPersonsOnce() : List<Person> {
        return dao.getAllPersonsOnce()
    }

    suspend fun insert(person: Person) {
        dao.insert(person)
    }

    suspend fun delete(person: Person) {
        dao.delete(person)
    }

    suspend fun update(person: Person) {
        dao.update(person)
    }


}
