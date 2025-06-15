package com.example.aquariusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquariusapp.database.AquariusDatabase
import com.example.aquariusapp.network.RetrofitClient
import com.example.aquariusapp.repository.EmpleadoRepository
import com.example.aquariusapp.ui.empleado.EmpleadoListScreen
import com.example.aquariusapp.ui.theme.AquariusAppTheme
import com.example.aquariusapp.viewmodel.EmpleadoViewModel
import com.example.aquariusapp.viewmodel.EmpleadoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear instancia de ViewModel con dependencia manual
        val database = AquariusDatabase.getDatabase(applicationContext)
        val dao = database.empleadoDao()
        val api = RetrofitClient.apiService
        val repository = EmpleadoRepository(dao, api)
        val viewModelFactory = EmpleadoViewModelFactory(repository)

        setContent {
            AquariusAppTheme {
                val viewModel: EmpleadoViewModel = viewModel(factory = viewModelFactory)

                EmpleadoListScreen(
                    viewModel = viewModel,
                    onEmpleadoClick = { /* Acción al tocar un empleado */ },
                    onAgregarClick = { /* Acción para agregar */ }
                )
            }
        }
    }
}
