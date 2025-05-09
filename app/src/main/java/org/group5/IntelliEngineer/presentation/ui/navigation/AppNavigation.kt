package org.group5.IntelliEngineer.presentation.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.group5.IntelliEngineer.data.auth.entity.User
import org.group5.IntelliEngineer.presentation.ui.screen.DatePickerScreen
import org.group5.IntelliEngineer.presentation.ui.screen.DropdownScreen
import org.group5.IntelliEngineer.presentation.ui.screen.FieldTextScreen
import org.group5.IntelliEngineer.presentation.ui.screen.HomeScreen
import org.group5.IntelliEngineer.presentation.ui.screen.auth.LoginScreen
import org.group5.IntelliEngineer.presentation.ui.screen.auth.RegisterScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.BatasanScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.ExperienceScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.ImplementationScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.ObjectiveScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.PerencanaanScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.ProjectScreen
import org.group5.IntelliEngineer.presentation.ui.screen.project.StatusScreen
import org.group5.IntelliEngineer.presentation.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentUser = authViewModel.currentUser

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                currentUser = currentUser,
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onItemSelected = { route ->
                    navController.navigate(route)
                    scope.launch { drawerState.close() }
                },
                onLogin = {
                    navController.navigate("login")
                    scope.launch { drawerState.close() }
                },
                onLogout =  {
                    authViewModel.logout()
                    navController.navigate("home")
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("home") { HomeScreen() }
                composable("projekscreen") { ProjectScreen() }
                composable("objectivescreen") { ObjectiveScreen() }
                composable("experiencescreen") { ExperienceScreen() }
                composable("implementationscreen") { ImplementationScreen() }
                composable("batasanscreen") { BatasanScreen() }
                composable("statusscreen") { StatusScreen() }
                composable("perencanaanscreen") { PerencanaanScreen() }
//                composable("dropdown") { DropdownScreen() }
//                composable("FieldText") { FieldTextScreen() }
//                composable("Datepickers") { DatePickerScreen() }
                composable("login") {
                    LoginScreen(
                        authViewModel = authViewModel,
                        onLoginSuccess = {
                            navController.popBackStack("home", inclusive = false)
                        },
                        onNavigateToRegister = {
                            navController.navigate("register")
                        }
                    )
                }
                composable("register") {
                    RegisterScreen(
                        authViewModel = authViewModel,
                        onBackToLogin = {
                            navController.popBackStack("login", inclusive = false)
                        },
                        onRegisterSuccess = {
                            navController.popBackStack("home", inclusive = false)
                        }
                    )
                }

            }
        }
    }
}

@Composable
private fun DrawerContent(
    currentUser: User?,
    onCloseDrawer: () -> Unit,
    onItemSelected: (String) -> Unit,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Text(
                "Menu Utama",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HorizontalDivider()
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable(enabled = currentUser == null) {
                        if (currentUser == null) onLogin()
                    }
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentUser?.username ?: "Masuk",
                    fontSize = 19.sp,
                    color = Color.Black
                    // color = if (currentUser == null) MaterialTheme.colorScheme.primary else Color.Black
                )
            }

            HorizontalDivider()
            Spacer(modifier = Modifier.height(4.dp))

            val menuItems = listOf(
                "Home" to "home",
                "Projek" to "projekscreen",
                "Meaningful Objective" to "objectivescreen",
                "Intellligence Experience" to "experiencescreen",
                "Intelligence Implementation" to "implementationscreen",
                "Batasan Perencanaan" to "batasanscreen",
                "Status Realisasi" to "statusscreen",
                "Perencaaan" to "perencaanscreen",
//                "Dropdown" to "dropdown",
//                "Field Text" to "FieldText",
//                "Date Pickers" to "Datepickers"
            )

            LazyColumn {
                items(menuItems) { (item, route) ->
                    val isEnabled = currentUser != null
                    Text(
                        text = item,
                        fontSize = 18.sp,
                        color = if (isEnabled) Color.DarkGray else Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable(enabled = isEnabled) {
                                onItemSelected(route)
                                onCloseDrawer()
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            if (currentUser != null) {
                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(25),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("LOG OUT", color = Color.White)
                }
            }
        }
    }
}


