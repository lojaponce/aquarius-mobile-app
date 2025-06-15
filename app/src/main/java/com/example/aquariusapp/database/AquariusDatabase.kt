package com.example.aquariusapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmpleadoEntity::class], version = 1, exportSchema = false)
abstract class AquariusDatabase : RoomDatabase() {
    abstract fun empleadoDao(): EmpleadoDao

    companion object {
        @Volatile
        private var INSTANCE: AquariusDatabase? = null

        fun getDatabase(context: android.content.Context): AquariusDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AquariusDatabase::class.java,
                    "aquarius_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
