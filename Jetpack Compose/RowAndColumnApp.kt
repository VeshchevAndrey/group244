// package com.example.application244 - здесь название вашего проекта

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            ColumnFunction("Jessie", "Trader", 27)
        }
    }
}

@Composable
fun ColumnFunction(name: String, post: String, age: Byte){
    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text("Имя: $name", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Должность: $post", fontWeight = FontWeight.Light)
        Text("Возраст: $age")
    }
}

@Composable
fun RowFunction(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Вы готовы?")
        Button(onClick = {}) { Text("Нажми на меня!") }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ColumnFunction("Walter", "Teacher", 50)
        RowFunction()
    }
}
