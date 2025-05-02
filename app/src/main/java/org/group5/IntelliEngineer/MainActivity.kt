package org.group5.IntelliEngineer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHost
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            IntelligenceEngineeringApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntelligenceEngineeringApp() {
    val navController = rememberNavController() //newline
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    //var thisScreen by remember { mutableStateOf("Home") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onItemSelected = { route ->
                    if (route.isNotEmpty()) {
                        navController.navigate(route)
                    }
                    scope.launch { drawerState.close() }
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {  },
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
                Modifier.padding(paddingValues)
            ) {
                composable("home") { IntelligenceEngineeringScreen() }
                composable("dropdown") { DropdownScreen()  }
                composable("FieldText") { FieldTextScreen() }
                composable("Datepickers") { DatepickerScreen() }
            }

        }
    }
}

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    onItemSelected: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp), // Drawer Width
        color = Color.White // Drawer Background Color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Menu Utama",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Divider()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Masuk",
                    fontSize = 19.sp,
                    color = Color.Black
                )
            }

            Divider()

            Spacer(modifier = Modifier.height(8.dp))

            val menuItems: List<Pair<String, String>> = listOf(
                "Home" to "home",
                "Dropdown" to "dropdown",
                "Field Text" to "FieldText",
                "Date Pickers" to "Datepickers"
//                "Projek" to "projek",
//                "Meaningful Objectives" to "",
//                "Intelligence Experience" to "",
//                "Intelligence Implementation" to "",
//                "Batasan Pengembangan" to "",
//                "Status Realisasi" to "",
//                "Perencanaan" to "",
            )

            LazyColumn {
                items(menuItems) { ( item, route) ->
                    Text(
                        text = item,
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                onItemSelected(route)
                                onCloseDrawer()
                            }
                    )
                }
            }
        }
    }
}


@Composable
fun IntelligenceEngineeringScreen(modifier: Modifier = Modifier) {
    val points = listOf(
        "Merekam meaningful objectives yang telah ditetapkan pelaksana proyek. Paling tidak ada 4 objectives yaitu: organizational objectives, leading indicator, user outcomes, dan model properties.",
        "Merekam intelligence experience, yang paling tidak berisi (1) penyajian kecerdasan berupa satu atau lebih cara yaitu automate, prompt, organisation, dan atau annotate, (2) fungsi-fungsi yang dapat merealisasikan meaningful objectives; (3) bagaimana meminimalkan kesalahan perangkat lunak; (4) pengumpulan data untuk perbaikan perangkat lunak.",
        "Merekam intelligence implementation, yang paling tidak berisi proses bisnis perangkat lunak, teknologi yang akan dipakai dalam setiap proses, dan identifikasi proses yang akan menjadikan keseluruhan sistem yang dibangun menjadi cerdas.",
        "Merekam hal-hal yang membatasi pengembangan modul Perangkat."
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(points) { point ->
                    Text(
                        text = "- $point",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.crudify),
            contentDescription = "Logo",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Intelligence Engineering", fontSize = 20.sp, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Kelompok 5", fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
    }
}
