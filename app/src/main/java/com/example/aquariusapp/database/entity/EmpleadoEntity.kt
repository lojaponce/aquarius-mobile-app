package com.example.aquariusapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empleado")
data class EmpleadoEntity(
    @PrimaryKey val id: Int,
    val nombres: String,
    val correo: String,
    val telefono: String,
    val area: String    // <-- agrega este campo aquí
)