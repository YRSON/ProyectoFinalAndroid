package com.example.projectaplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectaplication.db.entity.PersonaEntity

@Dao
interface PersonaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg persona: PersonaEntity)

    @Update
    fun actualizar(vararg persona: PersonaEntity)

    @Query("DELETE FROM persona")
    fun eliminarTodo()

    @Query("SELECT * FROM persona LIMIT 1")
    fun obtener(): LiveData<PersonaEntity>

}