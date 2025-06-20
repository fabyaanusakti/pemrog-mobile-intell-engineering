package org.group5.IntelliEngineer.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.group5.IntelliEngineer.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val points = listOf(
        "Merekam meaningful objectives yang telah ditetapkan pelaksana proyek. Paling tidak ada 4 objectives yaitu: organizational objectives, leading indicator, user outcomes, dan model properties.",
        "Merekam intelligence experience, yang paling tidak berisi (1) penyajian kecerdasan berupa satu atau lebih cara yaitu automate, prompt, organisation, dan atau annotate, (2) fungsi-fungsi yang dapat merealisasikan meaningful objectives; (3) bagaimana meminimalkan kesalahan perangkat lunak; (4) pengumpulan data untuk perbaikan perangkat lunak.",
        "Merekam intelligence implementation, yang paling tidak berisi proses bisnis perangkat lunak, teknologi yang akan dipakai dalam setiap proses, dan identifikasi proses yang akan menjadikan keseluruhan sistem yang dibangun menjadi cerdas.",
        "Merekam hal hal yang membatasi pengembangan modul Perangkat Lunak dan perekaman status realisasi modul Perangkat Lunak yang dikerjakan di aplikasi pengembangan modul Perangkat Lunak.",
        "Merekam perencanaan implementasi perangkat lunak, yang paling tidak menetapkan pelaksanaan deployment, pemeliharaan perangkat lunak, pelaksana operasi perangkat lunak."
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(points) { index, point ->
                    Text(
                        text = "${index + 1}. $point",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
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
            contentDescription = "Logo CRUDIFY",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp)) // Melengkung di keempat sisi
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "CRUDIFY",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Intelligence Engineering",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Kelompok 5",
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}
