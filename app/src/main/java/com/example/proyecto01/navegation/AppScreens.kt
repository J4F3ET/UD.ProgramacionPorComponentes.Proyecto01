package com.example.proyecto01.navegation

sealed class AppScreens(val router: String){
    object HomeScreen:AppScreens("homeScreen")
    object GameScreen:AppScreens("gameScreen ")
}
