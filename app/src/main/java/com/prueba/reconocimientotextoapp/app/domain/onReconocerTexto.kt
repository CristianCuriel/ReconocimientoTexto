package com.prueba.reconocimientotextoapp.app.domain

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.text.Text
import com.prueba.reconocimientotextoapp.app.data.CamaraRepository

class OnReconocerTexto() {

    private val camaraRepository = CamaraRepository()

     operator fun invoke(bitmap:Bitmap): Task<Text> {
        return camaraRepository.analizarTexto(bitmap)
    }

}