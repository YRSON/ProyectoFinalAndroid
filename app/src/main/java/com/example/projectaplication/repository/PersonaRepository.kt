package com.example.projectaplication.repository

import androidx.lifecycle.LiveData
import com.example.projectaplication.db.dao.PersonaDAO
import com.example.projectaplication.db.entity.PersonaEntity

class PersonaRepository(private val personaDAO: PersonaDAO) {
    suspend fun insertar(personaEntity: PersonaEntity){
        personaDAO.insertar(personaEntity)
    }
    suspend fun actualizar(personaEntity: PersonaEntity){
        personaDAO.actualizar(personaEntity)
    }

    //suspend -> suspenciones suspendidas para trabajar con corutinas
    suspend fun eliminarTodo(){
        personaDAO.eliminarTodo()
    }

    fun obtener(): LiveData<PersonaEntity>{
        return personaDAO.obtener()
    }
}