package com.prueba.reconocimientotextoapp.app.ui.views.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.prueba.reconocimientotextoapp.app.routes.Routes
import com.prueba.reconocimientotextoapp.app.ui.MainViewModel


@Composable
fun PhotoPreview(
    show: Boolean,
    bitmaps: Bitmap?,
    viewModel: MainViewModel,
    NavController: NavHostController,
    onDismiss: () -> Unit
) {

    if (show) {
        AlertDialog(onDismissRequest = { /*TODO*/ }, containerColor = Color.White, shape = RoundedCornerShape(8.dp),
            confirmButton = {
                TextButton(
                    onClick = {
                        if (bitmaps != null) {
                            viewModel.ReconocerTexto(bitmaps)
                        }
                        NavController.navigate(Routes.Screen3.route)
                    }
                ) {
                    Text("Analizar Imagen", color = Color.DarkGray)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss()}
                ) {
                    Text("Tomar otra foto", color = Color.DarkGray)
                }
            },

            title = {
                Text(
                    text = "Imagen Capturada",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            },

            text = {
                if (bitmaps != null) {
                    Image(
                        bitmap = bitmaps.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    )
                }else{
                    Text(text = "Imagen no tomada, ERROR")
                }
            }
        )
    }// show

}