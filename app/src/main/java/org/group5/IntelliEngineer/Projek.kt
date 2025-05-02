//package org.group5.IntelliEngineer.ui.screens
package org.group5.IntelliEngineer

// SettingsScreen.kt
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.foundation.layout.*
//import androidx.compose.runtime.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material3.*
//import androidx.compose.runtime.saveable.rememberSaveable
//
//@Composable
//fun ProjekScreen() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        val listKota = getKotaList() // Pastikan dipanggil dalam Surface
//        FieldTextEntry(listKota = listKota) // Panggil fungsi di dalam Surface
//    }
//}
//
//// Data class untuk daftar kota
//data class KotaList( val kota: String)
//
//// Fungsi untuk membuat daftar kota
//fun getKotaList(): List<KotaList> {
//    return listOf(
//        KotaList("Jakarta"),
//        KotaList("Banten"),
//    )
//}
//
//@Composable
//fun DropDownItemKota(listKota: List<KotaList>, onSelected: (String) -> Unit) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        for (item in listKota) {
//            DropdownMenuItem(
//                text = { Text(text = item.kota) },
//                onClick = { onSelected(item.kota) }
//            )
//        }
//    }
//}
//
//@Composable
//fun DropDownKota(listKota: List<KotaList>, mExpanded: Boolean, onSelected: (String) -> Unit) {
//    DropdownMenu(
//        expanded = mExpanded,
//        onDismissRequest = { /* Menutup dropdown saat di luar diklik */ },
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        DropDownItemKota(listKota = listKota, onSelected = onSelected)
//    }
//}
//
//@Composable
//fun FieldTextEntry(listKota: List<KotaList>) {
//    var kota by rememberSaveable { mutableStateOf("") }
//    var mExpanded by rememberSaveable { mutableStateOf(false) }
//
//    Column {
//        TextField(
//            value = kota,
//            onValueChange = {},
//            label = { Text(text = "Kota") },
//            trailingIcon = {
//                IconButton(onClick = { mExpanded = !mExpanded }) {
//                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "" )
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        DropDownKota(listKota = listKota, mExpanded = mExpanded) {
//            kota = it
//            mExpanded = false
//        }
//    }
//}


//@Composable
//fun ProjekScreen() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Settings Screen",
//                fontSize = 24.sp,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "This is the settings view.",
//                fontSize = 16.sp,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//        }
//    }
//}