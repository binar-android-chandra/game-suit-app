package com.example.game_suit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game_suit.ui.theme.GamesuitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamesuitTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val viewModel = MainViewModel()
    val options = viewModel.options
    val computerOption = viewModel.computerOption
    val result = viewModel.result

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Selamat Datang di Game Suit",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
            )
            Text(text = "Computer :", fontSize = 20.sp)
            Text(
                text = computerOption.value.ifEmpty { "?" }, fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            )
            Text(text = "Pemain :", fontSize = 20.sp)
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                modifier = Modifier.padding(bottom = 5.dp),
            ) {
                items(options.size) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Button(
                            onClick = { viewModel.startGame(options[it]) },
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                            enabled = result.value.isEmpty()
                        ) {
                            Text(
                                text = options[it],
                                fontSize = 15.sp,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
            Text(text = "Hasil :", fontSize = 20.sp)
            Text(
                text = result.value.ifEmpty { "..." }, fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                if (result.value.isNotEmpty()) {
                    Button(
                        onClick = { computerOption.value = ""; result.value = "" },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    ) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "reset",
                            modifier = Modifier.height(40.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GamesuitTheme {
        MainScreen()
    }
}