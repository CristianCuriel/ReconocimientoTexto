package com.prueba.reconocimientotextoapp.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prueba.reconocimientotextoapp.app.routes.Routes
import com.prueba.reconocimientotextoapp.app.ui.MainViewModel
import com.prueba.reconocimientotextoapp.app.ui.views.Screen1
import com.prueba.reconocimientotextoapp.app.ui.views.Screen2
import com.prueba.reconocimientotextoapp.app.ui.views.Screen3

@Composable
fun NavigationsViews(){

    val navigationController = rememberNavController()
    val viewModel = viewModel<MainViewModel>()

    NavHost(
        navController = navigationController,
        startDestination = Routes.Screen1.route,
    ) {
        composable(Routes.Screen1.route) { Screen1(navigationController, viewModel) }
        composable(Routes.Screen2.route) { Screen2(navigationController, viewModel) }
        composable(Routes.Screen3.route) { Screen3(navigationController, viewModel) }
    }//NavHost

}