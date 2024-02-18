package com.prueba.reconocimientotextoapp.app.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.prueba.reconocimientotextoapp.app.routes.Routes.Screen2
import com.prueba.reconocimientotextoapp.app.ui.MainViewModel


@Composable
fun Screen1(NavController: NavHostController, viewModel: MainViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { NavController.navigate(Screen2.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffB8F397)),
            elevation =  ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = "Abrir Camara",
                color = Color.DarkGray,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

