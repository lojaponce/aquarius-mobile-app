package com.example.aquariusapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {

    @Query("SELECT * FROM empleado")
    fun obtenerTodos(): Flow<List<EmpleadoEntity>> // para LiveData o Flow

    @Query("SELECT * FROM empleado")
    suspend fun obtenerTodosDirecto(): List<EmpleadoEntity> // para llamadas suspendidas

    @Query("SELECT * FROM empleado WHERE id = :id")
    suspend fun obtenerPorId(id: Int): EmpleadoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(empleado: EmpleadoEntity)

    @Update
    suspend fun actualizar(empleado: EmpleadoEntity)

    @Delete
    suspend fun eliminar(empleado: EmpleadoEntity)
}
