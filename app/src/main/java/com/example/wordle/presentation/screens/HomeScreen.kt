package com.example.wordle.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.R
@Preview
@Composable
fun HomeScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Spacer(modifier = Modifier.height(32.dp))
            InfoBody()
            Spacer(modifier = Modifier.height(32.dp))
            ExamplesSection()
            Spacer(modifier = Modifier.height(32.dp))
            Buttons()
        }
    }
}

@Composable
fun LogoHeader() {
    Box(
        modifier = Modifier.padding(horizontal = 3.dp)
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logowordle),
            contentDescription = "Logo de Wordle",
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun InfoBody() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(16.dp)
    ) {
        Text("How to play", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Guess the Wordle in 6 tries.\nEach guess must be a valid 5-letter word.\nThe color of the tiles will change to show how close your guess was to the word.",
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun ExamplesSection() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Examples", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(12.dp))
        ExampleRow("WORDY", listOf(Color.Green, Color.Black, Color.Black, Color.Black, Color.Black), "W is in the word and in the correct spot.")
        Spacer(modifier = Modifier.height(8.dp))
        ExampleRow("LIGHT", listOf(Color.Black, Color.Yellow, Color.Black, Color.Black, Color.Black), "I is in the word but in the wrong spot.")
        Spacer(modifier = Modifier.height(8.dp))
         ExampleRow("ROGUE", listOf(Color.Black, Color.Black, Color.Black, Color.Gray, Color.Black), "U is not in the word in any spot.")
    }
}

@Composable
fun ExampleRow(word: String, colors: List<Color>, description: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Row {
            word.forEachIndexed { index, char ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Black) // Fondo negro del cuadro
                        .border(2.dp, Color.Gray), // Borde del cuadro
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = char.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (colors[index] != Color.Gray) Color.White else Color.Gray,
                        modifier = Modifier.background(colors[index]) // Colorea solo la letra correspondiente
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(description, fontSize = 16.sp, color = Color.White)
    }
}


@Composable
fun Buttons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Button(onClick = { /* Acción de jugar */ }, shape = RoundedCornerShape(12.dp)) {
            Text("Let's play!", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { /* Acción de leaderboard */ }, shape = RoundedCornerShape(12.dp)) {
            Text("Leaderboard", fontSize = 20.sp)
        }
    }
}
