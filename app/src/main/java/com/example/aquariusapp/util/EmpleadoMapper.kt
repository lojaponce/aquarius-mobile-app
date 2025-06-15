package com.example.aquariusapp.util

import com.example.aquariusapp.database.EmpleadoEntity
import com.example.aquariusapp.model.Empleado

fun Empleado.toEntity(): EmpleadoEntity {
    return EmpleadoEntity(
        id = this.id,
        nombres = this.nombres,
        correo = this.correo,
        telefono = this.telefono,
        area = this.area // Solo si EmpleadoEntity tiene este campo, si no, elimina esta línea
    )
}

fun EmpleadoEntity.toEmpleado(): Empleado {
    return Empleado(
        id = this.id,
        nombres = this.nombres,
        correo = this.correo,
        telefono = this.telefono,
        area = this.area // Solo si Empleado tiene este campo, si no, elimina esta línea
    )
}
