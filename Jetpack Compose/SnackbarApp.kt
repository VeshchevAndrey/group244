// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
            ApplicationScreen()
        }
    }
}

// Пример вывода Snackbar
//@Composable
//fun ApplicationScreen(){
//    val snackbarHostState = remember { SnackbarHostState() }
//    val coroutineScope = rememberCoroutineScope()
//
//    Button(onClick = {
//        coroutineScope.launch {
//            snackbarHostState.showSnackbar("Ура, открытое окно!")
//        }
//    }) { Text(text = "Нажми меня!") }
//
//    SnackbarHost(hostState = snackbarHostState)
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen(){
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val resultText = remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = "Ого, нажали на супер-кнопку!")
                }
            }) {
                Icon(Icons.Rounded.Create, "Create")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = {
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Ого, всплывающее окно!",
                        duration = SnackbarDuration.Indefinite,
                        withDismissAction = true,
                        actionLabel = "Действие"
                    )
                    when (result) {
                        SnackbarResult.ActionPerformed -> { resultText.value = "Вы что-то сделали" }
                        SnackbarResult.Dismissed -> { resultText.value = "Ничего не сделали" }
                    }
                }
            }) { Text(text = "Нажми на меня!") }
            Text(text = resultText.value)
        }
    }
}
