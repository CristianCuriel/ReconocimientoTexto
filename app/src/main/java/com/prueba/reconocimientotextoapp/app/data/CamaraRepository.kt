package com.prueba.reconocimientotextoapp.app.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CamaraRepository()  {

    suspend fun takePhoto(
        context: Context,
        controller: LifecycleCameraController
    ): Bitmap  {
        val completer = CompletableDeferred<Bitmap>()

        controller.takePicture(
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    try {
                        // Procesamiento del ImageProxy para obtener un Bitmap
                        val matrix = Matrix().apply {
                            postRotate(image.imageInfo.rotationDegrees.toFloat())
                        }
                        val rotatedBitmap = Bitmap.createBitmap(
                            image.toBitmap(), // Extensión para convertir ImageProxy a Bitmap
                            0, 0,
                            image.width, image.height,
                            matrix,
                            true
                        )
                        // Completa el Deferred con el resultado
                        completer.complete(rotatedBitmap)
                    } catch (e: Exception) {
                        completer.completeExceptionally(e)
                    } finally {
                        image.close() // Asegúrate de cerrar el ImageProxy
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("Camera", "Couldn't take photo: ", exception)
                    completer.completeExceptionally(exception)
                }
            }
        )

        // Espera a que la imagen sea procesada y el resultado esté disponible
        return completer.await()
    }


     fun analizarTexto(bitmap: Bitmap): Task<Text> {

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap, 0)
//        val result = recognizer.process(image)
//          .addOnSuccessListener { visionText ->
//                // Task completed successfully
//                Log.e("Cris", visionText.text)
//            }
//            .addOnFailureListener { e ->
//                Log.e("Cris", e.toString())
//            }

         return recognizer.process(image)
    }


}