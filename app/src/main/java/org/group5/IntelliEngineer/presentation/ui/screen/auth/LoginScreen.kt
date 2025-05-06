package org.group5.IntelliEngineer.presentation.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthState
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val username = authViewModel.username
    val password = authViewModel.password
    val authState = authViewModel.authState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Masuk untuk melanjutkan", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { authViewModel.onUsernameChange(it) },
            label = { Text("Masukkan Username") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { authViewModel.onPasswordChange(it) },
            label = { Text("Masukkan Kata Sandi") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { authViewModel.login() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Masuk")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Belum punya akun?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Daftar",
                color = Color.Blue,
                modifier = Modifier.clickable{ onNavigateToRegister() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        when (authState) {
            is AuthState.Success -> {
                Text(authState.message)
                LaunchedEffect(Unit) {
                    onLoginSuccess()
                    authViewModel.resetAuthState()
                }
            }
            is AuthState.Error -> Text(authState.message, color = Color.Red)
            AuthState.Loading -> CircularProgressIndicator()
            else -> {}
        }
    }
}



