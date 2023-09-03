package com.example.proyecto01.services

import com.example.proyecto01.R

enum class Player(val imgDraw:Int,val animationRaw:Int) {
    PLAYER_A(imgDraw= R.drawable.blob,animationRaw= R.raw.blob),
    PLAYER_B(imgDraw= R.drawable.ghost,animationRaw= R.raw.ghos),
    PARACHUTE(imgDraw = R.drawable.parachute, animationRaw = R.raw.parachute),
    ROCKET(imgDraw = R.drawable.rocket, animationRaw = R.raw.cohete)
}