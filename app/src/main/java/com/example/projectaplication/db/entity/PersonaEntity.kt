package com.example.projectaplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
data class PersonaEntity(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val email: String,
    //val direccion: String,
    val celular: String,
    val usuario: String,
    val password: String,

)
