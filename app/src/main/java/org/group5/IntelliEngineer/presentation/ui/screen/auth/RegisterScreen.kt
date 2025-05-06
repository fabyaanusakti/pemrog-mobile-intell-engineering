package org.group5.IntelliEngineer.presentation.ui.screen.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthState
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val username = authViewModel.username
    val email = authViewModel.email
    val password = authViewModel.password
    val authState = authViewModel.authState

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onRegisterSuccess()
            authViewModel.resetAuthState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Register", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { authViewModel.onUsernameChange(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { authViewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { authViewModel.onPasswordChange(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (authState) {
            is AuthState.Success -> {
                LaunchedEffect(Unit) {
                    onRegisterSuccess()
                    authViewModel.resetAuthState()
                }
            }
            is AuthState.Error -> Text(authState.message, color = Color.Red)
            AuthState.Loading -> CircularProgressIndicator()
            else -> {}
        }

        Button(
            onClick = authViewModel::register,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Sudah Punya Akun?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Masuk",
                color = Color.Blue,
                modifier = Modifier.clickable { onBackToLogin() }
            )
        }


    }
}
