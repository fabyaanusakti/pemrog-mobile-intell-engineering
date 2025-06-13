package org.group5.IntelliEngineer.presentation.ui.screen.project

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectiveScreen() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val projectOptions = listOf("Animals", "Education", "Healthcare")
    var selectedProject by remember { mutableStateOf(projectOptions.first()) }
    var expanded by remember { mutableStateOf(false) }

    var organizationalObjectives by remember { mutableStateOf("") }
    var leadingIndicator by remember { mutableStateOf("") }
    var userOutcomes by remember { mutableStateOf("") }
    var modelProperties by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Meaningful Objectives",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Dropdown Project
        Column {
            Text("Pilih Projek:", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedProject,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
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
        }

        // Input fields kosong
        InputTextField("Organizational Objectives", organizationalObjectives) {
            organizationalObjectives = it
        }
        InputTextField("Leading Indicators", leadingIndicator) {
            leadingIndicator = it
        }
        InputTextField("User Outcomes", userOutcomes) {
            userOutcomes = it
        }
        InputTextField("Model Properties", modelProperties) {
            modelProperties = it
        }

        Spacer(modifier = Modifier.height(24.dp))

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
fun InputTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            maxLines = 3
        )
    }
}
