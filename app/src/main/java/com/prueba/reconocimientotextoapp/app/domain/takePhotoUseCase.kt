package com.prueba.reconocimientotextoapp.app.domain

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import com.prueba.reconocimientotextoapp.app.data.CamaraRepository

class TakePhotoUseCase() {

    private val camaraRepository = CamaraRepository()

    suspend operator fun invoke(
        context: Context,
        controller: LifecycleCameraController,
    ): Bitmap {
        return camaraRepository.takePhoto(context, controller)
    }

}