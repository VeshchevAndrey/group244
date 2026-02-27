package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application1()
        }
    }
}

@Composable
fun Application1(){
    val message = remember { mutableStateOf("") }
    val hiddenMessage = remember { mutableStateOf("") }
    Column() {
        Text(text = "My First App", fontSize = 20.sp)
        TextField(value = message.value, onValueChange = {message.value = it})
        Button(onClick = {
            if (message.value != ""){
                hiddenMessage.value = message.value
                message.value = ""
            }
        }) { Text(text = "Click on me!") }
        Text(hiddenMessage.value)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    Application1()
}
