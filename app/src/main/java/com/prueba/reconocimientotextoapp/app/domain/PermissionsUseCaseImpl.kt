package com.prueba.reconocimientotextoapp.app.domain

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsUseCaseImpl(
    private val context: Context
) : PermissionsUseCase {

    override fun hasCameraPermission(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            CAMERAX_PERMISSIONS,
            0
        )
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )
    }
}
