package com.example.aquariusapp.network

import com.example.aquariusapp.model.Empleado
import retrofit2.Response
import retrofit2.http.*

interface EmpleadoApiService {

    @GET("empleado/listar")
    suspend fun obtenerEmpleados(): Response<List<Empleado>>

    @POST("empleado/guardar")
    suspend fun guardarEmpleado(@Body empleado: Empleado): Response<Empleado>

    @PUT("empleado/editar")
    suspend fun actualizarEmpleado(@Body empleado: Empleado): Response<Empleado>

    @DELETE("empleado/eliminar/{id}")
    suspend fun eliminarEmpleado(@Path("id") id: Long): Response<Void>
}
