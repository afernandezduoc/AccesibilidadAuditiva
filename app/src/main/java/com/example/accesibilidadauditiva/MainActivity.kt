package com.example.accesibilidadauditiva

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.accesibilidadauditiva.ui.theme.AccesibilidadAuditivaTheme

class MainActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferences = UserPreferences(this)
        usuariosRegistrados = userPreferences.loadUsers()  // Cargar usuarios registrados

        setContent {
            AccesibilidadAuditivaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("home") { HomeScreen() }
                    composable("register") { RegisterScreen(navController) }
                    composable("recovery") { PasswordRecoveryScreen(navController) }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        userPreferences.saveUsers(usuariosRegistrados)  // Guardar usuarios cuando la app se detiene
    }
}

fun autenticarUsuario(email: String, password: String): Boolean {
    return usuariosRegistrados.any { it.email == email && it.password == password }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AccesibilidadAuditivaTheme {
        Greeting("Android")
    }
}
