package com.prueba.reconocimientotextoapp.app.domain

interface PermissionsUseCase {
    fun hasCameraPermission(): Boolean
    fun requestCameraPermission()
}


