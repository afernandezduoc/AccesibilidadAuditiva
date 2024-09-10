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
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Ajustes de la Aplicación",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Notificaciones")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = true, // Estado inicial del interruptor
                onCheckedChange = { /* Implementa la lógica para cambiar el estado */ }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tema oscuro")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = false, // Estado inicial del interruptor
                onCheckedChange = { /* Implementa la lógica para cambiar el tema */ }
            )
        }

        Button(
            onClick = { /* Implementa la lógica para cerrar sesión */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Cerrar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AccesibilidadAuditivaTheme {
        SettingsScreen()
    }
}
