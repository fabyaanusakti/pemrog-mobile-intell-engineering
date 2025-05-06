package org.group5.IntelliEngineer.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import org.group5.IntelliEngineer.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val points = listOf(
        "lorem Ipsum...")

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
        Text("Intelligence Engineering", fontSize = 20.sp, style = MaterialTheme.typography.headlineSmall)
        Text("Kelompok 5", fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
    }
}



