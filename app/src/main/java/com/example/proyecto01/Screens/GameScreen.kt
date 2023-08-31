package com.example.proyecto01.Screens

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.proyecto01.ui.theme.BackgroundImage
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController){
    Scaffold{
        BackgroundImage()
        GameBodyContent(navController)
    }
}
@Composable
fun GameBodyContent(navController: NavController){
    Button(onClick = {
    }) {

    }
}
