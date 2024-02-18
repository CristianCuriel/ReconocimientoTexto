package com.prueba.reconocimientotextoapp.app.ui.views

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.prueba.reconocimientotextoapp.app.ui.MainViewModel

@Composable
fun Screen3(NavController: NavHostController, viewModel: MainViewModel) {

    val photoBitmap by viewModel.photoBitmap.collectAsState()
    val Rtexto by viewModel.Rtexto.collectAsState()

    ViewBitmap(photoBitmap, Rtexto, )
}


@Composable
fun ViewBitmap(bitmaps: Bitmap?, Rtexto: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (bitmaps != null) {
                Image(
                    bitmap = bitmaps.asImageBitmap(),
                    //painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }// Row

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Card(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Informacion de la imagen",
                    Modifier.padding(vertical = 4.dp),
                    color = Color.DarkGray,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(text = Rtexto, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
            }
        }

    }

}
