package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application244.ui.theme.Application244Theme

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userRepository = UserRepository(this)
        val viewModel = UserViewModel(userRepository)
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application244Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(
                        modifier = Modifier.padding(paddingValues),
                        vm = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun ApplicationScreen(modifier: Modifier, vm: UserViewModel){
    val userName = vm.currentUser.collectAsState()

    Column(modifier = modifier) {
        TextField(value = vm.inputText.value, onValueChange = { vm.inputText.value = it })
        Button(onClick = {
            vm.updateName(vm.inputText.value)
            vm.inputText.value = ""
        }) { Text(text = "Сохранить") }
        Text(text = "Имя пользователя: ${userName.value}")
    }
}