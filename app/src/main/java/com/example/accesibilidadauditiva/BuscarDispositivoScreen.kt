package com.example.accesibilidadauditiva

import android.Manifest
import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.tasks.Task
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun BuscarDispositivoScreen(navController: NavHostController) {
    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Buscando ubicación...") }
    val locationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var currentLocation by remember { mutableStateOf<Location?>(null) }
    var locationPermissionGranted by remember { mutableStateOf(false) }

    // Solicitud de permisos
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            obtenerUbicacion(locationClient, context) { location ->
                currentLocation = location
            }
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Buscar Dispositivo Cercano",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                // Solicitar permisos de ubicación
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Buscar Ubicación", fontSize = 18.sp)
        }

        // Mostrar mapa si se tiene la ubicación
        currentLocation?.let { location ->
            val currentLatLng = LatLng(location.latitude, location.longitude)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(currentLatLng, 15f)
            }

            // Recordar el estado del marcador
            val markerState = rememberMarkerState(position = currentLatLng)

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(currentLatLng, 15f)
                }
            ) {
                Marker(
                    state = MarkerState(position = currentLatLng),
                    title = "Ubicación actual"
                )
            }

        }

        if (!locationPermissionGranted) {
            Text(
                text = locationText,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

private fun obtenerUbicacion(
    fusedLocationClient: FusedLocationProviderClient,
    context: Context,
    onLocationObtained: (Location?) -> Unit
) {
    fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
        if (task.isSuccessful && task.result != null) {
            onLocationObtained(task.result)
        } else {
            Toast.makeText(context, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuscarDispositivoScreenPreview() {
    val navController = rememberNavController()
    BuscarDispositivoScreen(navController)
}
