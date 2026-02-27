package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            ModifierFunction()
        }
    }
}

@Composable
fun ModifierFunction(){
    Column(
        modifier = Modifier
            .background(Color(0xFF1B5E20))
            .fillMaxWidth()
    ) {
        Text(
            text = "Walter White",
            modifier = Modifier
                .padding(5.dp) // Задаёт внешний отступ объекту
                .background(Color(0xFF4CAF50)) // Задаёт задний фон объекту
                .padding(5.dp) // Задаёт внутренний отступ объекту
                .fillMaxWidth(), // Задаёт ширину объекта во весь родительский контейнер
            fontSize = 20.sp
        )
        Text(
            text = "Teacher",
            modifier = Modifier
                .padding(5.dp, 0.dp, 5.dp, 5.dp)
                .background(Color(0xFF66BB6A))
                .padding(5.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Subscribe",
            modifier = Modifier
                .clickable(onClick = {}) // Указывает, что объект является кликабельным
                .align(Alignment.CenterHorizontally) // Задаёт горизонтальное выравнивание внутри колонки
                .padding(5.dp)
                .background(Color(0xFF000000))
                .padding(10.dp),
            color = Color(0xFFFFFFFF) // Задаёт цвет тексту
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    ModifierFunction()
}
