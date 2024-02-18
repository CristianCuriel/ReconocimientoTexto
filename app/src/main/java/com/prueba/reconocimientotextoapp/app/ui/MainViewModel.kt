package com.prueba.reconocimientotextoapp.app.ui

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.text.Text
import com.prueba.reconocimientotextoapp.app.data.CamaraRepository
import com.prueba.reconocimientotextoapp.app.domain.OnReconocerTexto
import com.prueba.reconocimientotextoapp.app.domain.PermissionsUseCaseImpl
import com.prueba.reconocimientotextoapp.app.domain.TakePhotoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {

    private val onReconocerTexto = OnReconocerTexto()
    private val takePhotoUseCase = TakePhotoUseCase()

    private val _Rtexto = MutableStateFlow<String>("")
    val Rtexto: StateFlow<String> = _Rtexto.asStateFlow()

    private val _photoBitmap = MutableStateFlow<Bitmap?>(null)
    val photoBitmap: StateFlow<Bitmap?> = _photoBitmap.asStateFlow()

    private val _checkPermissions = MutableStateFlow<Boolean>(false)
    val checkPermissions = _checkPermissions.asStateFlow()

    //Para mostrar el modal
    private val _showPreview = MutableStateFlow<Boolean>(false)
    val showPreview = _showPreview.asStateFlow()

    fun changedShow() {
        _showPreview.value = !_showPreview.value
    }

    fun onTakeCapturePicture(context: Context, controller: LifecycleCameraController) {
        viewModelScope.launch {

            try {
                val bitmap = takePhotoUseCase(context, controller)
                _photoBitmap.value = bitmap
                changedShow()
            }catch (e : Exception){
                Log.i("Cris", e.toString())
            }

        }
    }

    fun ReconocerTexto(bitmap: Bitmap){
            onReconocerTexto(bitmap).addOnSuccessListener { _Rtexto.value = it.text }
    }

    private fun checkCameraPermissions(context: Context): Boolean {
        return PermissionsUseCaseImpl(context).hasCameraPermission().also {
            _checkPermissions.value = it
        }
    }

    fun requestCameraPermissions(context: Context) {
        PermissionsUseCaseImpl(context).requestCameraPermission()
        checkCameraPermissions(context)
    }

} //MainViewModel