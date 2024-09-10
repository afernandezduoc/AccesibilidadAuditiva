import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accesibilidadauditiva.usuariosRegistrados

@Composable
fun LoginScreen(navController: NavHostController, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido",
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

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (autenticarUsuario(email, password)) {
                    mensajeError = null
                    onLoginSuccess()
                    navController.navigate("home") // Navega a la HomeScreen
                } else {
                    mensajeError = "Credenciales incorrectas"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Iniciar Sesión", fontSize = 18.sp)
        }

        mensajeError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        TextButton(
            onClick = { navController.navigate("recovery") }
        ) {
            Text("¿Olvidaste tu contraseña?")
        }

        TextButton(
            onClick = { navController.navigate("register") }
        ) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

fun autenticarUsuario(email: String, password: String): Boolean {
    return usuariosRegistrados.any { it.email == email && it.password == password }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Se crea un controlador de navegación falso para la vista previa
    val navController = NavHostController(LocalContext.current)
    LoginScreen(navController = navController, onLoginSuccess = {})
}
