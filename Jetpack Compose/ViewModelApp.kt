// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application244.ui.theme.Application244Theme


// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            CounterScreen()
        }
    }
}

// Реализация "всё в одном"
//@Composable
//fun CounterScreen() {
//    val count = rememberSaveable() { mutableStateOf(0) }
//
//    Column() {
//        Button(onClick = { count.value++ }) { Text(text = "Кликай!") }
//        Text(text = "Вы кликнули ${count.value} раз")
//    }
//}

// Реализация через ViewModel
class CounterViewModel : ViewModel() {
    val count = mutableStateOf(0)

    fun increase(){
        count.value++
    }
}

@Composable
fun CounterScreen(vm: CounterViewModel = viewModel()) {
    Column() {
        Button(onClick = { vm.increase() }) { Text(text = "Кликай!") }
        Text(text = "Вы кликнули ${vm.count.value} раз")
    }
}
