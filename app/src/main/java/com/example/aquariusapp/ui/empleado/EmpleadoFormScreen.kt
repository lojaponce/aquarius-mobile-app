package com.example.aquariusapp.ui.empleado

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aquariusapp.model.Empleado
import com.example.aquariusapp.viewmodel.EmpleadoViewModel
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun EmpleadoFormScreen(
    viewModel: EmpleadoViewModel,
    empleadoExistente: Empleado? = null,
    onGuardar: () -> Unit
) {
    var nombres by rememberSaveable { mutableStateOf(empleadoExistente?.nombres ?: "") }
    var correo by rememberSaveable { mutableStateOf(empleadoExistente?.correo ?: "") }
    var telefono by rememberSaveable { mutableStateOf(empleadoExistente?.telefono ?: "") }
    var area by rememberSaveable { mutableStateOf(empleadoExistente?.area ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nombres,
            onValueChange = { nombres = it },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = area,
            onValueChange = { area = it },
            label = { Text("Área") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val nuevoEmpleado = Empleado(
                    id = empleadoExistente?.id ?: 0,
                    nombres = nombres,
                    correo = correo,
                    telefono = telefono,
                    area = area
                )
                if (empleadoExistente == null) {
                    viewModel.agregarEmpleado(nuevoEmpleado)
                } else {
                    viewModel.actualizarEmpleado(nuevoEmpleado)
                }
                onGuardar()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (empleadoExistente == null) "Guardar" else "Actualizar")
        }
    }
}
