package org.group5.IntelliEngineer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun DropdownScreen() {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        val listKota = getKotaList()
        FieldTextEntry(listKota = listKota)
    }
}

data class KotaList( val kota: String)

fun getKotaList(): List<KotaList> {
    return listOf(
        KotaList("Jakarta"),
        KotaList("Banten"),
    )
}

@Composable
fun DropDownItemKota(listKota: List<KotaList>, onSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (item in listKota) {
            DropdownMenuItem(
                text = { Text(text = item.kota) },
                onClick = { onSelected(item.kota) }
            )
        }
    }
}

@Composable
fun DropDownKota(listKota: List<KotaList>, mExpanded: Boolean, onSelected: (String) -> Unit) {
    DropdownMenu(
        expanded = mExpanded,
        onDismissRequest = { /* Menutup dropdown saat di luar diklik */ },
        modifier = Modifier.fillMaxWidth()
    ) {
        DropDownItemKota(listKota = listKota, onSelected = onSelected)
    }
}

@Composable
fun FieldTextEntry(listKota: List<KotaList>) {
    var kota by rememberSaveable { mutableStateOf("") }
    var mExpanded by rememberSaveable { mutableStateOf(false) }

    Column {
        TextField(
            value = kota,
            onValueChange = {},
            label = { Text(text = "Kota") },
            trailingIcon = {
                IconButton(onClick = { mExpanded = !mExpanded }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "" )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropDownKota(listKota = listKota, mExpanded = mExpanded) {
            kota = it
            mExpanded = false
        }
    }
}