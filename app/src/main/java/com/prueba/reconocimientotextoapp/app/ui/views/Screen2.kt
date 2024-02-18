package com.prueba.reconocimientotextoapp.app.ui.views

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.prueba.reconocimientotextoapp.app.ui.MainViewModel
import com.prueba.reconocimientotextoapp.app.ui.views.component.CameraPreview
import com.prueba.reconocimientotextoapp.app.ui.views.component.PhotoPreview


@Composable
fun Screen2(NavController: NavHostController, viewModel: MainViewModel) {

    val context = LocalContext.current

    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }

    viewModel.requestCameraPermissions(context)

    MyScafold(controller, context, viewModel, NavController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScafold(
    controller: LifecycleCameraController,
    context: Context,
    viewModel: MainViewModel,
    NavController: NavHostController,
) {

    val checkPermissions by viewModel.checkPermissions.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onTakeCapturePicture(context, controller)
                },
                shape = CircleShape,
                containerColor = Color(0xffB8F397)
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Crear"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        topBar = { TopAppBar() }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {

            val showPreview by viewModel.showPreview.collectAsState()
            val photoBitmap by viewModel.photoBitmap.collectAsState()

            PhotoPreview(showPreview, photoBitmap, viewModel, NavController){ viewModel.changedShow() }

            if (checkPermissions) {
                CameraPreview(
                    controller = controller,
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                SolicitarPermiso(context, viewModel)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Photo,
                        contentDescription = "Open gallery"
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "Take photo"
                    )
                }
            }
        }
    }
}

@Composable
fun SolicitarPermiso(context: Context, viewModel: MainViewModel) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Faltan permisos para la camara")
        Button(
            onClick = {
                viewModel.requestCameraPermissions(context)
            }, modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Solicitar permiso")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    androidx.compose.material3.TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            Text("Tomar fotografia")
        },

        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Hacia atras"
                )
            }
        },

        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Favorito")
            }
        }

    )
}
