// package com.example.myapplication - здесь название Вашего приложения 

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            StateFunction()
        }
    }
}

@Composable
fun StateFunction(){
    val message = remember { mutableStateOf("Нажми на меня") }
    val rotateMessage = rememberSaveable { mutableStateOf("Нажми, я изменюсь!") }
    var changeColor by rememberSaveable() { mutableStateOf(0xFFFF6D00)  }
    Column() {
        CheckFunction("Ты ел сегодня?")
        CheckFunction("Ты хорошо спал?")
        CheckFunction("Ты хорошо себя чувствуешь?")
        RadioFunction()
        Text(
            text = message.value,
            modifier = Modifier.clickable(onClick = {message.value = "Спасибо!"}),
            fontSize = 50.sp
        )
        Text(
            text = rotateMessage.value,
            modifier = Modifier
                .clickable(onClick = {
                    rotateMessage.value = "Вращай сколько угодно!!"
                    changeColor = 0xFFFFFFFF
                })
                .background(color = Color(changeColor)),
            fontSize = 50.sp
        )
    }
}

@Composable
fun CheckFunction(question: String){
    val check = remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = check.value,
            onCheckedChange = {check.value = it}
        )
        Text(text = question)
    }
}

@Composable
fun RadioFunction(){
    var check by remember { mutableStateOf(true) }
    Row() {
        RadioButton(
            selected = check,
            onClick = {check = true}
        )
        RadioButton(
            selected = !check,
            onClick = {check = false}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
//    StateFunction()

}
