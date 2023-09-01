package com.example.proyecto01.Screens
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto01.Navegation.AppScreens
import com.example.proyecto01.R
import com.example.proyecto01.ui.theme.BackgroundImage



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold{
        BackgroundImage()
        HomeBodyContent(navController)
    }
}
@Composable
fun HomeBodyContent(navController: NavController ){
    Column (
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Cielo en Pasos",
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.rubiksfadee_normal, FontWeight.Normal)),
                fontWeight = FontWeight.Normal,
                fontSize = 50.sp, // Cambia el tamaño del texto
                lineHeight = 55.sp, // Ajusta la altura de línea
                letterSpacing = 0.sp,
                shadow = Shadow(
                    color = Color(0xFFFFFFFF), // Color del sombreado (negro)
                    offset = Offset(2f, 2f), // Desplazamiento del sombreado en dp
                    blurRadius = 15f // Radio de desenfoque del sombreado en dp
                )
            )
        )
        TextButton(
            onClick ={
                navController.navigate(route = AppScreens.GameScreen.router)
            },
            modifier = Modifier.padding(16.dp).background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            // Contenido del botón
            Text(text = "Jugar",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.rubiksfadee_normal, FontWeight.Normal)),
                    fontWeight = FontWeight.Normal),
                    fontSize = 30.sp, // Cambia el tamaño del texto
                    lineHeight = 35.sp, // Ajusta la altura de línea
                    letterSpacing = 0.sp,
            )
        }
    }
}

