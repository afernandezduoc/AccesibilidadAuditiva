package com.example.accesibilidadauditiva

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUsers(users: List<Usuario>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(users)
        editor.putString("usuarios_registrados", json)
        editor.apply()
    }

    fun loadUsers(): MutableList<Usuario> {
        val gson = Gson()
        val json = sharedPreferences.getString("usuarios_registrados", null)
        val type = object : TypeToken<MutableList<Usuario>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }
}
