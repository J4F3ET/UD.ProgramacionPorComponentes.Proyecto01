package com.example.proyecto01.screens
import android.annotation.SuppressLint
import android.util.Log.d
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.example.proyecto01.ui.theme.BackgroundImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Scaffold{
        BackgroundImage()
        BodyContent()
    }
}
@Composable
fun BodyContent(){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Cielo en Pasos")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "2 Jugadores")
            
        }
    }
    
}
@Preview(widthDp = 568, heightDp = 320)
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}