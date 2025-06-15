package com.example.aquariusapp.ui.empleado

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aquariusapp.model.Empleado
import com.example.aquariusapp.viewmodel.EmpleadoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpleadoListScreen(
    viewModel: EmpleadoViewModel,
    onEmpleadoClick: (Empleado) -> Unit,
    onAgregarClick: () -> Unit
) {
    val empleados by viewModel.empleados.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Empleados") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarClick) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(empleados) { empleado ->
                EmpleadoItem(empleado = empleado, onClick = { onEmpleadoClick(empleado) })
            }
        }
    }
}

@Composable
fun EmpleadoItem(empleado: Empleado, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nombre: ${empleado.nombres}")
            Text("Correo: ${empleado.correo}")
        }
    }
}
