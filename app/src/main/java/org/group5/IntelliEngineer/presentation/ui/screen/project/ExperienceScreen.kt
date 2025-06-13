@file:OptIn(ExperimentalMaterial3Api::class)

package org.group5.IntelliEngineer.presentation.ui.screen.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExperienceScreen() {
    val scrollState = rememberScrollState()
    val projectOptions = listOf("Animals", "Plants", "Robotics")
    var selectedProject by remember { mutableStateOf(projectOptions.first()) }
    var expanded by remember { mutableStateOf(false) }

    var automate by remember { mutableStateOf("") }
    var prompt by remember { mutableStateOf("") }
    var annotate by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf("") }
    var achieveObjectives by remember { mutableStateOf("") }
    var minimizeFlaws by remember { mutableStateOf("") }
    var createDataToGrow by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Intelligence Experience",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Dropdown Proyek
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedProject,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Projek") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                projectOptions.forEach { project ->
                    DropdownMenuItem(
                        text = { Text(project) },
                        onClick = {
                            selectedProject = project
                            expanded = false
                        }
                    )
                }
            }
        }

        // Text Fields Kosong
        CustomInputField("Automate", automate) { automate = it }
        CustomInputField("Prompt", prompt) { prompt = it }
        CustomInputField("Annotate", annotate) { annotate = it }
        CustomInputField("Organization", organization) { organization = it }
        CustomInputField("Achieve System Objectives", achieveObjectives) { achieveObjectives = it }
        CustomInputField("Minimize Intelligence Flaws", minimizeFlaws) { minimizeFlaws = it }
        CustomInputField("Create Data to Grow", createDataToGrow) { createDataToGrow = it }

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

@Composable
fun CustomInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
