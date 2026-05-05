package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application244.ui.theme.Application244Theme

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        val toDoRepository = TaskRepository(this)
        val viewModel = ToDoViewModel(toDoRepository)
        setContent {
            Application244Theme() {
                Scaffold() { paddingValues ->
                    ToDoScreen(modifier = Modifier.padding(paddingValues), viewModel)
                }
            }
        }
    }
}

@Composable
fun ToDoScreen(modifier: Modifier = Modifier, vm: ToDoViewModel){
    val tasks = vm.tasks.collectAsState()

    Column(modifier = modifier) {
        Text(text = "Выполнено ${tasks.value.count { it.status }} задач из ${tasks.value.size}")
        Row() {
            TextField(
                value = vm.newTaskTitle.value,
                onValueChange = { vm.newTaskTitle.value = it }
            )
            Button(onClick = {
                vm.addTask(vm.newTaskTitle.value)
                vm.newTaskTitle.value = ""
            }) { Text(text = "Добавить") }
        }
        LazyColumn() {
            items(items = tasks.value){ task ->
                TaskItem(
                    task = task,
                    onStatusChange = { vm.toggleTaskStatus(task = task) },
                    onDelete = { vm.deleteTask(task = task) }
                )
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