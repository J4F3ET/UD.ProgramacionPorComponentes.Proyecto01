package com.example.proyecto01.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto01.screens.*
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = AppScreens.HomeScreen.router){
        composable(route = AppScreens.HomeScreen.router){HomeScreen(navController)}
        composable(route = AppScreens.GameScreen.router){GameScreen(navController)}
    }
}