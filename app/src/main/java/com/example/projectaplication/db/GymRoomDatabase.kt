package com.example.projectaplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.projectaplication.db.dao.PersonaDAO
import com.example.projectaplication.db.entity.PersonaEntity

@Database(entities = [PersonaEntity::class], version = 1)
abstract class GymRoomDatabase : RoomDatabase() {
    abstract fun personaDao(): PersonaDAO

    //Todo lo que tenga este bloque serán definidos como static
    companion object {
        @Volatile
        private var INSTANCE: GymRoomDatabase? = null

        fun getDatabase(context: Context): GymRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymRoomDatabase::class.java,
                    "gymdb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}