package com.example.prkha2082.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.prkha2082.data.Person
import com.example.prkha2082.repositories.PersonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PersonViewModel(private val repository: PersonRepository,application: Application) : AndroidViewModel(application) {

    val persons: StateFlow<List<Person>> =
        repository.allPersons.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun personOnce(): List<Person> = repository.getAllPersonsOnce()

    fun addPerson(name: String, telephone: Long, birthDate: String) {
        viewModelScope.launch {
            repository.insert(Person(name = name, telephone = telephone, birthDate = birthDate))
        }
    }

    fun removePerson(id: Int, name: String, telephone: Long, birthDate: String) {
        viewModelScope.launch {
            repository.delete(Person(id = id, name = name, telephone = telephone, birthDate = birthDate))
        }
    }

    fun updatePerson(id: Int, name: String, telephone: Long, birthDate: String) {
        viewModelScope.launch {
            repository.update(Person(id = id, name = name, telephone = telephone, birthDate = birthDate))
        }
    }
}
