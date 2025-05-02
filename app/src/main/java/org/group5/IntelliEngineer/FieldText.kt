package org.group5.IntelliEngineer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FieldTextScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        FieldTextEntry()
    }
}

@Composable
fun FieldTextEntry(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }
    var copiedText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(48.dp)) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it
                copiedText = it },
            label = { Text(text = "Masukan Teks") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { copiedText = inputText },
            modifier = Modifier.width(125.dp)
        ) {
            Text(text = "Salin Teks")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = copiedText,
            onValueChange = { copiedText = it },
            label = { Text(text = "Salinan Teks") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
    }
}