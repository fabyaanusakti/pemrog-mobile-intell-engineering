@file:OptIn(ExperimentalMaterial3Api::class)
package org.group5.IntelliEngineer.presentation.ui.screen.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusScreen() {
    val scrollState = rememberScrollState()
    val projectOptions = listOf("Animals", "Smart Home", "Retail AI")
    var selectedProject by remember { mutableStateOf(projectOptions.first()) }
    var expanded by remember { mutableStateOf(false) }

    var developmentLimitations by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Status Realisasi",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Dropdown Project
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedProject,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Projek") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                projectOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedProject = option
                            expanded = false
                        }
                    )
                }
            }
        }

        // Input besar: Batasan Pengembangan
        OutlinedTextField(
            value = developmentLimitations,
            onValueChange = { developmentLimitations = it },
            label = { Text("Status Realisasi") },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            maxLines = 10
        )

        // Tombol Simpan
        Button(
            onClick = { /* Aksi Simpan */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3C5A73)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Simpan", color = Color.White)
        }
    }
}
