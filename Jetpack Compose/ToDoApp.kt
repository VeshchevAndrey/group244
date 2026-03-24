// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview


// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {

        }
    }
}

@Composable
fun ToDoScreen(){
    val tasks = remember { mutableStateListOf<Task>() }
    val newTaskTitle = remember { mutableStateOf("") }

    Column() {
        Text(text = "Выполнено ${tasks.count { it.status }} задач из ${tasks.size}")
        Row() {
            TextField(
                value = newTaskTitle.value,
                onValueChange = { newTaskTitle.value = it }
            )
            Button(
                onClick = {
                    if (newTaskTitle.value.isNotEmpty()){
                        tasks.add(Task(title = newTaskTitle.value))
                        newTaskTitle.value = ""
                    }
                }
            ) { Text(text = "Добавить") }
        }
        LazyColumn() {
            items(items = tasks){
                TaskItem(
                    task = it,
                    onStatusChange = {
                        val index = tasks.indexOf(it)
                        tasks[index] = it.copy(status = !it.status)
                    },
                    onDelete = {
                        tasks.remove(it)
                    })
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onStatusChange: () -> Unit, onDelete: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.status,
            onCheckedChange = { onStatusChange() }
        )
        Text(
            text = task.title,
            textDecoration = if (task.status) TextDecoration.LineThrough else null,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { onDelete() }
        ) { Icon(Icons.Rounded.Delete, "Delete") }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    ToDoScreen()
}

data class Task(
    val title: String,
    val status: Boolean = false
)

