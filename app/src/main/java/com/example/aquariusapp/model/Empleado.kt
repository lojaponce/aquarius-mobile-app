package com.example.aquariusapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa un empleado en la base de datos local.
 */
@Entity(tableName = "empleado")
data class Empleado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,            // ID autogenerado por Room
    val nombres: String,        // Nombres del empleado
    val correo: String,         // Correo electrónico
    val telefono: String,       // Teléfono de contacto
    val area: String            // Área o departamento
)
