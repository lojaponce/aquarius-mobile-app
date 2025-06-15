package com.example.aquariusapp.repository

import com.example.aquariusapp.database.EmpleadoDao
import com.example.aquariusapp.model.Empleado
import com.example.aquariusapp.network.EmpleadoApiService
import com.example.aquariusapp.util.toEmpleado
import com.example.aquariusapp.util.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.aquariusapp.util.toEntity

class EmpleadoRepository(
    private val empleadoDao: EmpleadoDao,
    private val apiService: EmpleadoApiService
) {
    val empleados: Flow<List<Empleado>> = empleadoDao.obtenerTodos()
        .map { lista -> lista.map { it.toEmpleado() } }

    suspend fun obtenerTodos(): List<Empleado> {
        val empleadosLocal = empleadoDao.obtenerTodosDirecto()
        return empleadosLocal.map { it.toEmpleado() }
    }

    suspend fun insertar(empleado: Empleado) {
        insertarLocal(empleado)
        guardarEnServidor(empleado)
    }

    suspend fun actualizar(empleado: Empleado) {
        actualizarLocal(empleado)
        guardarEnServidor(empleado)
    }

    suspend fun eliminar(id: Int) {
        val empleadoEntity = empleadoDao.obtenerPorId(id)
        if (empleadoEntity != null) {
            val empleado = empleadoEntity.toEmpleado() // ✅ conversión aquí
            eliminarLocal(empleado)
            try {
                apiService.eliminarEmpleado(id.toLong())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun insertarLocal(empleado: Empleado) = withContext(Dispatchers.IO) {
        empleadoDao.insertar(empleado.toEntity())
    }

    suspend fun actualizarLocal(empleado: Empleado) = withContext(Dispatchers.IO) {
        empleadoDao.actualizar(empleado.toEntity())
    }

    suspend fun eliminarLocal(empleado: Empleado) = withContext(Dispatchers.IO) {
        empleadoDao.eliminar(empleado.toEntity())
    }

    suspend fun sincronizarDesdeApi() {
        try {
            val response = apiService.obtenerEmpleados()
            if (response.isSuccessful) {
                response.body()?.let { lista ->
                    withContext(Dispatchers.IO) {
                        for (empleado in lista) {
                            empleadoDao.insertar(empleado.toEntity())
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun guardarEnServidor(empleado: Empleado) {
        try {
            apiService.guardarEmpleado(empleado)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
