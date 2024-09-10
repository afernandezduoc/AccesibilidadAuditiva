package com.example.accesibilidadauditiva

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screenRoute: String) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, "home")
    object Profile : BottomNavItem("Perfil", Icons.Filled.Person, "profile")
    object Settings : BottomNavItem("Ajustes", Icons.Filled.Settings, "settings")
}
