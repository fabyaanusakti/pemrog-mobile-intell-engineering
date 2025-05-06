package org.group5.IntelliEngineer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import org.group5.IntelliEngineer.data.database.AppDatabase
import org.group5.IntelliEngineer.data.repository.UserRepository
import org.group5.IntelliEngineer.presentation.ui.navigation.MainApp
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthViewModel
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(applicationContext)
        val repository = UserRepository(db.userDao())
        val viewModel = ViewModelProvider(this, AuthViewModelFactory(repository))[AuthViewModel::class.java]

        installSplashScreen()
        setContent {
            MainApp(viewModel) //AppNavigation.kt
        }
    }
}


