# Documentación

El juego de escaleras y serpientes es un juego de mesa para dos o más jugadores. El objetivo del juego es llegar desde el inicio (casilla 1) hasta el final (casilla 100) antes que los demás jugadores, evitando las serpientes y aprovechando las escaleras que se encuentran en el tablero. El tablero de juego está formado por una cuadrícula de 10x10 casillas, numeradas de 1 a 100.

Para representar los objetos de la aplicación se utilizaron las siguientes imágenes:
### 1. Serpiente

|Paracaídas|Pasto|
|:---:|:---:|
|<img src="app\src\main\res\drawable\parachute.png" alt="Paracaídas" style="max-width: 100px; max-height: 50px;" />|<img src="app\src\main\res\drawable\grass.png" alt="Pasto" style="max-width: 100px; max-height: 50px;" />|
|El paracaídas se utilizó para representar la cabeza de la serpiente.|El pasto se utilizó para representar la cola de la serpiente.|
### 2. Escalera
|Cohete|Tierra|
|:---:|:---:|
|<img src="app\src\main\res\drawable\rocket.png" alt="Tierra" style="max-width: 200px; max-height: 150px;" />|<img src="app\src\main\res\drawable\earth.png" alt="Tierra" style="max-width: 200px; max-height: 150px;" />|
|El cohete se utilizó para representar la parte inferior de la escalera.|La tierra se utilizó para representar la parte superior de la escalera.|
### 3. Jugadores
|Jugador 1|Jugador 2|
|:---:|:---:|
|<img src="app\src\main\res\drawable\blob.png" alt="Jugador 1" style="max-width: 200px; max-height: 150px;" />|<img src="app\src\main\res\drawable\ghost.png" alt="Jugador 2" style="max-width: 200px; max-height: 150px;" />|
|El jugador 1 se representa con un slime.|El jugador 2 se representa con un fantasma.|

## Código
Para el desarrollo de la aplicación se utilizo el lenguaje de programacion Kotlin. Se utilizo el IDE Android Studio para el desarrollo de la aplicación. Se utilizaron herramientas de Jeck Pack Compose para el desarrollo de la interfaz gráfica.

La idea general de la logica del juego es la siguiente:
```kotlin
data class GameState(
    var board: List<BoardCell>,
    var currentPlayer: Player,
    var selectedCell: Int,
    var winner: Player?
)
```
El estado del juego se representa con un objeto de tipo GameState. Este objeto contiene la lista de celdas del tablero, el jugador actual, la celda seleccionada y el ganador del juego.

```kotlin
data class BoardCell(
    var position: Int,
    var player: Player?,
    var player2: Player?,
    var detourEntity: Player?,
    var detour: Position?,
)
```
Cada celda del tablero se representa con un objeto de tipo BoardCell.
1. `position` es la posición de la celda en el tablero.
2. `player` es el jugador que se encuentra en la celda, si es que se encuentra en la celda sino es null.
3. `player2` es el jugador que se encuentra en la celda, si es que se encuentra en la celda sino es null.
4. `detourEntity` es la entidad que se encuentra en la celda, si es que se encuentra en la celda sino es null.
- Entidad, puede ser una serpiente o una escalera.
5. `detour` son las coordenadas en el tablero a las cuales se debe mover el jugador si es que se encuentra una entidad en la celda, sino es null.


```kotlin
enum class Player(val imgDraw:Int,val animationRaw:Int,var peerCounts:Int) {
    PLAYER_A(imgDraw= R.drawable.blob,animationRaw= R.raw.blob,0),
    PLAYER_B(imgDraw= R.drawable.ghost,animationRaw= R.raw.ghost,0),
    PARACHUTE(imgDraw = R.drawable.parachute, animationRaw = R.raw.parachute,0),
    ROCKET(imgDraw = R.drawable.rocket, animationRaw = R.raw.cohete,0),
    EARTH(imgDraw = R.drawable.earth, animationRaw = R.raw.earth,0),
    GRASS(imgDraw = R.drawable.grass, animationRaw = R.raw.grass,0),
    MOON(imgDraw = R.drawable.moon, animationRaw = R.raw.moon,0),
}
```
Los jugadores se representan con un objeto de tipo Player. Este objeto contiene la imagen que representa al jugador y las entidades del juego.

## Requerimientos
1. ### Los dados se pueden representar mediante un número aleatorio.

El cumplimiento de este requerimiento se puede ver en el siguiente código:
```kotlin
fun updateGame() {
    if (gameState.winner != null) return
    val oldGameState = gameState.copy()
    currentThrow1 = (1..6).random()
    currentThrow2 = (1..6).random()
    gameState.currentPlayer.peerCounts = if(currentThrow1==6 || currentThrow2 == 6) gameState.currentPlayer.peerCounts++ else 0
    gameState = if (gameState.winner == null)updateGameState(currentThrow1 + currentThrow2, gameState)else gameState
    speak(viewModel, context ,gameState,oldGameState)
}
```
Que se encuentra en la clase `GameScreen.kt` en el paquete `package com.example.proyecto01.screens`.
Dentro del componente `GameScreen` se encuentra la función `updateGame` que se encarga de actualizar el estado del juego. En esta función se generan dos números aleatorios que representan los dados del juego. Estos números se generan con la función `random()` de la clase `IntRange` que se encuentra en el paquete `kotlin.ranges`. Estos números se guardan en las variables `currentThrow1` y `currentThrow2`. Estas variables se utilizan para mostrar los dados en la interfaz gráfica.

2. ### El juego debe ser de dos jugadores (no debe ser online).

Al rededor del codigo de la aplicacion se puede evidenciar que el juego es de dos jugadores. En el siguiente código se puede ver que el juego se inicializa con dos jugadores.
```kotlin
fun createInitialBoard(): List<BoardCell>
{
    val initialBoard = mutableListOf<BoardCell>()
    val listRocket = generateRocketPositions()
    val listParachute = generateParachutePositions()
    for (position in 0..(SettingGame.numCells)) {
        if (position == 0)
            initialBoard.add(BoardCell(position,Player.PLAYER_A,Player.PLAYER_B,null,null))
        else
            initialBoard.add(
                BoardCell(
                    position = position,
                    player = null,
                    player2 = null,
                    detourEntity = verificateStartDetourEntity(listRocket,listParachute,position)?:verificateEndDetourEntity(listRocket,listParachute,position),
                    detour = verificateStartDetour(listRocket,listParachute,position)?:verificateEndDetour(listRocket,listParachute,position)
                )
            )
    }
    return initialBoard
}
```
Dentro del archivo `utilGame.kt` en el paquete `package com.example.proyecto01.services.util` se encuentra la función `createInitialBoard` que se encarga de crear el tablero inicial del juego. En esta función se puede ver que se inicializa el tablero con dos jugadores. En la posición 1 se encuentra los jugadores y en las demás posiciones no se encuentra ningún jugador.

3. ### En cada turno se debe tirar dos dados (dos números aleatorios), esos números deben ser mostrados al jugador.
```kotlin
@Composable
fun floatingActionButtonGameScreen(currentThrow1: Int, currentThrow2: Int) {
    @Composable
    fun newColumn(currentThrow:Int){
        val textStyle = TextStyle(
            color = Color.White,
            fontSize = 10.sp, // Cambia el tamaño del texto
            lineHeight = 25.5.sp, // Ajusta la altura de línea
            letterSpacing = 0.sp
        )
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Color.White, shape = ExtraSmall)
                .padding(16.dp)
        ) {
            Text(
                text = "$currentThrow",
                style = textStyle
            )
        }
    }
    Row{
        newColumn(currentThrow = currentThrow1)
        Spacer(modifier = Modifier.width(8.dp))
        newColumn(currentThrow = currentThrow2)
    }
}
```
En el archivo `ComponentsControlPanelGameScreen.kt` en el paquete `package com.example.proyecto01.screens.components` se encuentra el componente `floatingActionButtonGameScreen` que se encarga de mostrar los dados en la interfaz gráfica. Este componente recibe como parámetros los dos números aleatorios que representan los dados del juego. Estos números se muestran en dos columnas que se encuentran dentro de un `Row`.

4. ### Se deben notificar que jugador gana
```kotlin
fun speakWinner(viewModel: SoundViewModel, context: Context, player: Player){
    val stateViewModel = viewModel.state.value
    stateViewModel.text = "Jugador " +if(player == Player.PLAYER_A){"1"} else {"2"} +" gano"
    viewModel.onTextFieldValue(stateViewModel.text)
    viewModel.textToSpeech(context)
}
```
En el archivo `utilGame.kt` en el paquete `package com.example.proyecto01.services.util` se encuentra la función `speakWinner` que se encarga de notificar al jugador que gano. Esta función recibe como parámetros el `SoundViewModel`, el `Context` y el jugador que gano. Esta función se encarga de cambiar el texto que se va a reproducir y de llamar a la función `textToSpeech` del `SoundViewModel` para reproducir el texto.

5. ### En cada turno se debe pintar el tablero y mostrar la posición en la que va el jugador 1 y jugador 2.
El tablero cada vez que se actualiza el estado del juego se pinta en la interfaz gráfica. En el siguiente código se puede ver como se pinta el tablero.
```kotlin
@Composable
fun GameScreen(navController: NavController,viewModel: SoundViewModel = viewModel()){
    var gameState by remember { mutableStateOf(createInitialGameState()) }
    var currentThrow1 by remember { mutableStateOf(0) }
    var currentThrow2 by remember { mutableStateOf(0) }
    val context = LocalContext.current
    fun updateGame() {
        if (gameState.winner != null) return
        val oldGameState = gameState.copy()
        currentThrow1 = (1..6).random()
        currentThrow2 = (1..6).random()
        gameState.currentPlayer.peerCounts = if(currentThrow1==6 || currentThrow2 == 6) gameState.currentPlayer.peerCounts++ else 0
        gameState = if (gameState.winner == null)updateGameState(currentThrow1 + currentThrow2, gameState)else gameState
        speak(viewModel, context ,gameState,oldGameState)
        gameState.winner?.let { winner ->
            speakWinner(viewModel, context, winner)
        }

    }
    Scaffold(
        topBar = {topBarGameScreen(navController,gameState)},
        bottomBar = { bottomBarGameScreen(currentThrow1, currentThrow2,gameState,::updateGame)}
    )
    {
        BackgroundImage()
        GameBodyContent(navController,gameState)
    }
}
```

6. ### Es obligatorio realizar interfaz gráfica o Activitys, no es necesario hacer directamente la imagen que se muestra, si no es de libre elección.
La interfaz gráfica se realizo con Jet Pack Compose
## Reglas

1. ### Si un jugador saca tres veces 6 en tres turnos diferentes, debe volver a empezar en la casilla inicial.
En la clase Player exite un parametro llamado peerCounts que se encarga de contar las veces que el jugador saca 6. En el siguiente código se puede ver como se actualiza este parametro.
```kotlin
newPosition = if(gameState.currentPlayer.peerCounts == 3) 0 else newPosition // Si lleva tres "6" acumulados
```
Esta linea se encuentra el la funcion `updateGameState` del packete `package com.example.proyecto01.services.util` en el archivo `utilGame.kt`.

El punto es que cada vez que el jugador actual es re escrito se reinicia el contador de `peerCounts` a 0.


2. ### Cuando se llega a la casilla final ese jugador es el que gana.
En el siguiente código se puede ver como se actualiza el estado del juego cuando un jugador llega a la casilla final.
```kotlin
val newGameState = if(position==6 && newPosition != SettingGame.numCells-1){
        GameState(
            board = updatedBoard,
            currentPlayer = currentPlayer,
            selectedCell = newPosition,
            winner = null
        )
    }else{
        GameState(
            board = updatedBoard,
            currentPlayer = if (gameState.currentPlayer == Player.PLAYER_A) Player.PLAYER_B else Player.PLAYER_A,
            selectedCell = positionPlayerOpossite,
            winner = if(newPosition == SettingGame.numCells-1) currentPlayer else null
        )
    }
```
Esta linea se encuentra el la funcion `updateGameState` del packete `package com.example.proyecto01.services.util` en el archivo `utilGame.kt`.

3. ### Al llegar a una escalera el jugador sube de casilla.
```kotlin
newPosition = if(updatedBoard[newPosition].detour == null) newPosition else updatedBoard[newPosition].detour!!.endPosition//Si para nueva ubicacion existe un detour (atajo)
```
Esta linea se encuentra el la funcion `updateGameState` del packete `package com.example.proyecto01.services.util` en el archivo `utilGame.kt`.


4. ### Al llegar a una cabeza de serpiente el jugador baja de casilla.

Esta regla se cumple con la misma linea de codigo que la regla anterior.

5. ### Si la persona saca 6 (sumando los dos dados) puede intentar un nuevo turno.
```kotlin
val newGameState = if(position==6 && newPosition != SettingGame.numCells-1){
        GameState(
            board = updatedBoard,
            currentPlayer = currentPlayer,
            selectedCell = newPosition,
            winner = null
        )
    }else{
        GameState(
            board = updatedBoard,
            currentPlayer = if (gameState.currentPlayer == Player.PLAYER_A) Player.PLAYER_B else Player.PLAYER_A,
            selectedCell = positionPlayerOpossite,
            winner = if(newPosition == SettingGame.numCells-1) currentPlayer else null
        )
    }
```
Esta linea se encuentra el la funcion `updateGameState` del packete `package com.example.proyecto01.services.util` en el archivo `utilGame.kt`.
## [APK ](\app\release)
La apk se encuentra en la carpeta release del proyecto. `app\release\app-release.apk`
