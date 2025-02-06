package com.example.wordle.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen() {
    var words by remember { mutableStateOf(List(6) { "" }) } // Lista de palabras ingresadas
    var currentRow by remember { mutableStateOf(0) } // Controla en qué fila estamos escribiendo

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "WORDLE", color = Color.White, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Acción para volver atrás */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF121212))
            )
        },
        containerColor = Color(0xFF121212)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Grid de letras con las palabras ingresadas
            LetterGrid(words)

            // Teclado
            Keyboard { letter ->
                if (currentRow < 6 && words[currentRow].length < 5) {
                    words = words.toMutableList().apply {
                        this[currentRow] = this[currentRow] + letter // ✅ Se modifica correctamente
                    }
                }
            }

            // Botón Submit
            SubmitButton {
                if (currentRow < 5) {
                    currentRow++
                }
            }
        }
    }
}

@Composable
fun LetterGrid(words: List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        words.forEach { word ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.DarkGray, shape = RoundedCornerShape(4.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = word.getOrNull(index)?.toString() ?: "",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Keyboard(onLetterClick: (Char) -> Unit) {
    val rows = listOf(
        "QWERTYUIOP",
        "ASDFGHJKL",
        "ZXCVBNM"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        rows.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { letter ->
                    Button(
                        onClick = { onLetterClick(letter) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        modifier = Modifier
                            .width(40.dp)
                            .height(48.dp)
                    ) {
                        Text(
                            text = letter.toString(),
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SubmitButton(onSubmit: () -> Unit) {
    Button(
        onClick = { onSubmit() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = "Submit",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}
