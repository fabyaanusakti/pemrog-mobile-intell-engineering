package org.group5.IntelliEngineer.presentation.ui.screen.project

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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

    // Dropdown state
    val projectOptions = listOf("Proyek A", "Proyek B", "Proyek C")
    var selectedProject by remember { mutableStateOf(projectOptions.first()) }
    var expanded by remember { mutableStateOf(false) }

    // Form field states
    var organizationalObjectives by remember { mutableStateOf("") }
    var leadingIndicator by remember { mutableStateOf("") }
    var userOutcomes by remember { mutableStateOf("") }
    var modelProperties by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("16/3/2025") }
    var endDate by remember { mutableStateOf("16/5/2025") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Meaningfull Objectives",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Dropdown untuk pilih proyek
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedProject,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Projek") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
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

        // Input Fields
        InputTextField("Organizational Objectives", organizationalObjectives) {
            organizationalObjectives = it
        }
        InputTextField("Leading Indicator", leadingIndicator) {
            leadingIndicator = it
        }
        InputTextField("User Outcomes", userOutcomes) {
            userOutcomes = it
        }
        InputTextField("Model Properties", modelProperties) {
            modelProperties = it
        }

        // Date Pickers
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            DateInputField("Tanggal Mulai", startDate) { selected ->
                startDate = selected
            }
            DateInputField("Tanggal Selesai", endDate) { selected ->
                endDate = selected
            }
        }

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* Simpan */ }) {
                Text("Simpan")
            }
            Button(onClick = { /* Lanjut */ }) {
                Text("Selanjutnya")
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }
        }
    }
}

@Composable
fun InputTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DateInputField(label: String, date: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(context, { _: DatePicker, y, m, d ->
        onDateSelected("$d/${m + 1}/$y")
    }, year, month, day)

    OutlinedTextField(
        value = date,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "Pilih Tanggal",
                modifier = Modifier.clickable { datePickerDialog.show() }
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}
