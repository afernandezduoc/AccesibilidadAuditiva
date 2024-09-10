package com.example.accesibilidadauditiva

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accesibilidadauditiva.ui.theme.AccesibilidadAuditivaTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Perfil de Usuario",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = "Nombre de Usuario", // Aquí podrías mostrar el nombre del usuario actual
            onValueChange = { /* Implementa la lógica para actualizar el nombre */ },
            label = { Text("Nombre Completo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = "Correo Electrónico", // Aquí podrías mostrar el correo del usuario actual
            onValueChange = { /* Implementa la lógica para actualizar el correo */ },
            label = { Text("Correo Electrónico") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = { /* Lógica para guardar los cambios en el perfil */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar Cambios")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AccesibilidadAuditivaTheme {
        ProfileScreen()
    }
}
