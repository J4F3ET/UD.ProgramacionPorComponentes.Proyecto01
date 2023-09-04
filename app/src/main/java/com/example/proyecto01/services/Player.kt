package com.example.proyecto01.services

import com.example.proyecto01.R

enum class Player(val imgDraw:Int,val animationRaw:Int,var peerCounts:Int) {
    PLAYER_A(imgDraw= R.drawable.blob,animationRaw= R.raw.blob,0),
    PLAYER_B(imgDraw= R.drawable.ghost,animationRaw= R.raw.ghost,0),
    PARACHUTE(imgDraw = R.drawable.parachute, animationRaw = R.raw.parachute,0),
    ROCKET(imgDraw = R.drawable.rocket, animationRaw = R.raw.cohete,0),
    EARTH(imgDraw = R.drawable.earth, animationRaw = R.raw.earth,0),
    GRASS(imgDraw = R.drawable.grass, animationRaw = R.raw.grass,0),
    MOON(imgDraw = R.drawable.moon, animationRaw = R.raw.moon,0),
}
