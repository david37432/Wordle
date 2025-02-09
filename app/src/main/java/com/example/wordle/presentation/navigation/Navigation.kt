package com.example.wordle.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import GameScreen
import com.example.wordle.presentation.screens.HomeScreen
import com.example.wordle.presentation.screens.ScoreScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home")
    {
        composable("home") {
            HomeScreen()
        }
        composable("game") {
            GameScreen()
        }
        composable("score") {
            ScoreScreen()
        }
    }
}