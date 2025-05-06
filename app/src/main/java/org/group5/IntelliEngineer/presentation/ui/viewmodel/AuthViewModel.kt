package org.group5.IntelliEngineer.presentation.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.group5.IntelliEngineer.data.auth.entity.User
import org.group5.IntelliEngineer.data.repository.UserRepository

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var currentUser by mutableStateOf<User?>(null)
        private set

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var authState by mutableStateOf<AuthState>(AuthState.Idle)
        private set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun resetAuthState() {
        authState = AuthState.Idle
    }

    fun logout() {
        currentUser = null
        authState = AuthState.Idle
    }



//    private fun isValidPassword(password: String): Boolean {
//        val passwordPattern = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
//        return password.matches(passwordPattern)
//    }


    fun login() {
        viewModelScope.launch {
            authState = AuthState.Loading
            try {
                val user = repository.loginUser(username, password)
                authState = if (user != null) {
                    currentUser = user
                    AuthState.Success("Login successful")
                } else {
                    AuthState.Error("Invalid credentials")
                }
            } catch (e: Exception) {
                authState = AuthState.Error("Login failed: ${e.message}")
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            authState = AuthState.Loading
            try {

                // Email validation
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    authState = AuthState.Error("Invalid email address")
                    return@launch
                }

                // Password validation
                if (!isValidPassword(password)) {
                    authState = AuthState.Error("Password must be at least 8 characters long.")
                    // Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.
                    return@launch
                }

                // Username validation
                val usernameExists = repository.checkUsernameExists(username)
                if (usernameExists) {
                    authState = AuthState.Error("Username already taken")
                    return@launch
                }

                val user = User(username = username, email = email, password = password)
                repository.registerUser(user)

                currentUser = user
                authState = AuthState.Success("Registration successful")
            } catch (e: Exception) {
                authState = AuthState.Error("Registration failed: ${e.message}")
            }
        }
    }


}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}


