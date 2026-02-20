// package com.example.application244 - вставьте название Вашего проекта

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            val myName = "Андрей"
            WelcomeFunction(myName)
        }
    }
}

// Пример простой функции с отрисовкой интерфейса
@Composable // аннотация, указывающая, что функция задаёт элементы интерфейса и взаимодействие с ними
fun WelcomeFunction(name: String){
    Text("Welcome back, $name!")
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    WelcomeFunction("Andrey")
}

// Пример функции с условной конструкцией
@Composable
fun TimesOfDayFunction(hour: Byte){
    var timesNow = ""
    if (hour in 0..5) timesNow = "night"
    else if (hour in 6..11) timesNow = "morning"
    else timesNow = "day"
    Text("Good $timesNow!")
}
