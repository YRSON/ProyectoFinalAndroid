package com.example.projectaplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projectaplication.db.GymRoomDatabase
import com.example.projectaplication.db.entity.PersonaEntity
import com.example.projectaplication.repository.PersonaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//corutinas para no deterner hilos del hilo principal
class PersonaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonaRepository

    init {
        val personaDao = GymRoomDatabase.getDatabase(application)
            .personaDao()
        repository = PersonaRepository(personaDao)
    }

    fun insertar(personaEntity: PersonaEntity) =
        //viewModelScope -> crea la corutina -> hilo secundario
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertar(personaEntity)
        }

    fun actualizar(personaEntity: PersonaEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.actualizar(personaEntity)
        }

    fun eliminarTodo() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.eliminarTodo()
        }

    fun obtener(): LiveData<PersonaEntity> {
        return repository.obtener()
    }

}