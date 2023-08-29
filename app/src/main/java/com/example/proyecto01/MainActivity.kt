package com.example.proyecto01
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto01.ui.theme.Proyecto01Theme
import com.example.proyecto01.ui.theme.BackgroundImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BackgroundImage()
                }
            }
        }
    }
}
@Preview(widthDp = 568, heightDp = 320)
@Composable
fun BackgroundImagePreview() {
    Proyecto01Theme {
        BackgroundImage()
    }
}
@Composable
fun ControlPanel(){
    Button(onClick = {

    }) {

    }
}









