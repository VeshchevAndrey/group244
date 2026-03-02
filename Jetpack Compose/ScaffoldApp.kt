package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Опросник") },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF6D00))
                    )
                }
            ) {
                Question("Ты человек?", modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun Question(question: String, modifier: Modifier = Modifier){
    val answers = arrayOf("Да", "Нет", "Не уверен")
    val (currentAnswer, onAnswerSelected) = remember { mutableStateOf(answers[0]) }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = question,
            modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier.selectableGroup()
        ) {
            answers.forEach { answer ->
                Answer(answer, currentAnswer, onAnswerSelected)
            }
        }
    }
}

@Composable
fun Answer(name: String, curAnsw: String, selected: (String) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (name == curAnsw),
            onClick = {selected(name)}
        )
        Text(
            text = name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    Question("Ты человек?")
}
