package com.example.game_suit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel: ViewModel() {
    val options = listOf("ROCK", "SCISSORS", "PAPER")
    val computerOption = mutableStateOf("")
    val result = mutableStateOf("")

    private val rules = mapOf(
        "ROCK-SCISSORS" to true,
        "ROCK-PAPER" to false,
        "SCISSORS-PAPER" to true,
        "SCISSORS-ROCK" to false,
        "PAPER-ROCK" to true,
        "PAPER-SCISSORS" to false,
    )

    private fun pickRandomOption(): String = options[Random.nextInt(0, 3)]

    private fun isDraw(from: String, to: String): Boolean = from == to

    private fun isWin(from: String, to: String): Boolean? = rules["$from-$to"]

    fun startGame(option: String) {
        val randomOption = pickRandomOption()
        computerOption.value = randomOption

        result.value = when {
            isDraw(option, randomOption) -> "Draw"
            isWin(option, randomOption) == true -> "You Won"
            else -> "You Lose"
        }
    }
}