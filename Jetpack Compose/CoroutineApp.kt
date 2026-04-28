// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.application244.ui.theme.Application244Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            AppScreen()
        }
    }
}

@Composable
fun AppScreen(){
    val coroutineScope = rememberCoroutineScope()
    val counterState = remember { mutableStateOf(0) }
    val enabledState = remember { mutableStateOf(true) }
    val pauseState = remember { mutableStateOf(0) }
    val progressState = remember { mutableStateOf(0.0f) }

    Column() {
        Button(onClick = {
            coroutineScope.launch { imitateWork() }
        }) { Text(text = "Нажми меня!") }
        Button(onClick = { counterState.value++ }) { Text(text = "Кликнул ${counterState.value} раз!") }
        Text(text = "Счётчик: ${pauseState.value}")
        Button(onClick = {
            coroutineScope.launch {
                enabledState.value = false
                for (i in 1..5){
                    pauseState.value = i
                    progressState.value += 0.2f
                    delay(1000)
                }
                enabledState.value = true
                progressState.value = 0.0f
            }
        }, enabled = enabledState.value) { Text(text = "Запусти счётчик!") }
        LinearProgressIndicator(progress = { progressState.value })
        CircularProgressIndicator(progress = { progressState.value })
    }
}

// Объявление приостанавливаемой функции
suspend fun imitateWork(){
    println("Начало имитации работы")
    delay(5000)
    println("Окончание имитации работы")
}

// Области корутин:
// GlobalScope - корутины, привязанные ко всему жизненному циклу приложение (могут продолжать работать при выходе их приложения)
// ViewModelScope - привязаны к конкретному ViewModel
// LifecycleScope - привязаны к конкретному компоненту с жизненным циклом
