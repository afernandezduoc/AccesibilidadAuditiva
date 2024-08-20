package com.example.accesibilidadauditiva

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accesibilidadauditiva.usuariosRegistrados

@Composable
fun PasswordRecoveryScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Recuperar Contraseña",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                // Verificar si el correo está registrado
                val usuario = usuariosRegistrados.find { it.email == email }
                if (usuario != null) {
                    mensaje = "Su contraseña es: ${usuario.password}"
                } else {
                    mensaje = "Correo no registrado"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Recuperar Contraseña", fontSize = 18.sp)
        }

        mensaje?.let {
            Text(
                text = it,
                color = if (it.startsWith("Su contraseña es")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        TextButton(
            onClick = { navController.navigate("login") }
        ) {
            Text("Volver al Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordRecoveryScreenPreview() {
    val navController = rememberNavController()
    PasswordRecoveryScreen(navController)
}
