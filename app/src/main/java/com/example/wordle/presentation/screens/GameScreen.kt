import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleScreen()
        }
    }
}
@Preview
@Composable
fun WordleScreen() {
    val letters = listOf(
        "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
        "A", "S", "D", "F", "G", "H", "J", "K", "L",
        "Z", "X", "C", "V", "B", "N", "M"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Acción para regresar */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "WORDLE",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        WordGrid()
        Spacer(modifier = Modifier.height(16.dp))
        Keyboard(letters)

        Button(
            onClick = { /* Acción de enviar palabra */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text("Submit", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun WordGrid() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        repeat(6) {
            Row(modifier = Modifier.padding(2.dp)) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.DarkGray, RoundedCornerShape(8.dp))
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Keyboard(letters: List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val row1 = letters.take(10)
        val row2 = letters.drop(10).take(9)
        val row3 = letters.drop(19)

        KeyboardRow(row1)
        KeyboardRow(row2)
        KeyboardRow(row3)
    }
}

@Composable
fun KeyboardRow(letters: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        letters.forEach { letter ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.DarkGray, CircleShape)
                    .padding(8.dp)
                    .clickable { /* Acción al presionar una letra */ },
                contentAlignment = Alignment.Center
            ) {
                Text(letter, color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
