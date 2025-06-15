package com.example.aquariusapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariusapp.model.Empleado
import com.example.aquariusapp.repository.EmpleadoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmpleadoViewModel(private val repository: EmpleadoRepository) : ViewModel() {

    private val _empleados = MutableStateFlow<List<Empleado>>(emptyList())
    val empleados: StateFlow<List<Empleado>> = _empleados

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    init {
        cargarEmpleados()
    }

    fun cargarEmpleados() {
        viewModelScope.launch {
            val lista = repository.obtenerTodos()
            _empleados.value = lista
        }
    }

    fun agregarEmpleado(empleado: Empleado) {
        viewModelScope.launch {
            repository.insertar(empleado)
            _mensaje.value = "Empleado guardado"
            cargarEmpleados()
        }
    }

    fun actualizarEmpleado(empleado: Empleado) {
        viewModelScope.launch {
            repository.actualizar(empleado)
            _mensaje.value = "Empleado actualizado"
            cargarEmpleados()
        }
    }

    fun eliminarEmpleado(id: Int) {
        viewModelScope.launch {
            repository.eliminar(id)
            _mensaje.value = "Empleado eliminado"
            cargarEmpleados()
        }
    }

    fun limpiarMensaje() {
        _mensaje.value = null
    }
}
