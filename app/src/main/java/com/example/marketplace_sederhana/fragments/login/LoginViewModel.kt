package com.example.marketplace_sederhana.fragments.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    fun register(username: String, password: String): Boolean {
        if (username.isBlank() || password.isBlank()) {
            return false
        }

        // Check if username already exists
        if (sharedPreferences.contains(username)) {
            return false
        }

        // Store the new credentials
        sharedPreferences.edit().apply {
            putString(username, password)
            apply()
        }
        return true
    }

    fun login(username: String, password: String): Boolean {
        if (username.isBlank() || password.isBlank()) {
            return false
        }

        val storedPassword = sharedPreferences.getString(username, null)
        return storedPassword == password
    }

    fun isUserRegistered(username: String): Boolean {
        return sharedPreferences.contains(username)
    }
}