package com.example.accesibilidadauditiva

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.accesibilidadauditiva.ui.*
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
                var isAuthenticated by remember { mutableStateOf(false) } // Estado de autenticación

                MainScreen(navController, isAuthenticated) { isAuthenticated = true }  // Actualizar el estado de autenticación cuando el usuario inicie sesión
            }
        }
    }

    override fun onStop() {
        super.onStop()
        userPreferences.saveUsers(usuariosRegistrados)  // Guardar usuarios cuando la app se detiene
    }
}

@Composable
fun MainScreen(navController: NavHostController, isAuthenticated: Boolean, onLoginSuccess: () -> Unit) {
    Scaffold(
        bottomBar = {
            if (isAuthenticated) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(navController, onLoginSuccess) // Pasar la función para actualizar el estado de autenticación
            }
            composable("home") { HomeScreen(navController) }
            composable("register") { RegisterScreen(navController) }
            composable("recovery") { PasswordRecoveryScreen(navController) }
            composable("profile") { ProfileScreen() }
            composable("settings") { SettingsScreen(navController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: ""
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Profile,
            BottomNavItem.Settings
        )

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
