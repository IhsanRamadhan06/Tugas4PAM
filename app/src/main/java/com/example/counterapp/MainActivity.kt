package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel()
) {
    // Ambil state dari ViewModel
    val appUiState by appViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. NIM dan Nama
        Text(text = "Nama: Ihsan Ramadhan", fontSize = 18.sp)
        Text(text = "NIM: 245150407111063", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(32.dp))

        // Angka counter
        Text(text = appUiState.counter.toString(), fontSize = 72.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Tombol Up, Down, Reset (event dikirim ke ViewModel)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { appViewModel.down() }) {
                Text("Down")
            }
            Button(onClick = { appViewModel.up() }) {
                Text("Up")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { appViewModel.reset() }) {
            Text("Reset")
        }
    }
}