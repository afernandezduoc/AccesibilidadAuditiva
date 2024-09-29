package com.example.accesibilidadauditiva

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
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
import java.util.*

@Composable
fun HablarScreen(navController: NavHostController) {
    var mensaje by remember { mutableStateOf("") }
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    // Inicializar Text-to-Speech
    LaunchedEffect(Unit) {
        tts = TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                tts?.language = Locale.getDefault()
            } else {
                Toast.makeText(context, "Error al inicializar Text-to-Speech", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Limpiar Text-to-Speech al salir de la pantalla
    DisposableEffect(Unit) {
        onDispose {
            tts?.shutdown()
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
            text = "Escribe y Escucha",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = mensaje,
            onValueChange = { mensaje = it },
            label = { Text("Escribe un mensaje") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (mensaje.isNotEmpty()) {
                    // Convertir texto a voz
                    tts?.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "")
                } else {
                    Toast.makeText(context, "Escribe un mensaje para hablar", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Hablar", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HablarScreenPreview() {
    val navController = rememberNavController()
    HablarScreen(navController = navController)
}
