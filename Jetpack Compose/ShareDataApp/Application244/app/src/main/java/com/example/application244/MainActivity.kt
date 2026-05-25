package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application244.ui.theme.Application244Theme

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val profileRepository = ProfileRepository(this)
        val viewModel = ProfileViewModel(profileRepository)

        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application244Theme(dynamicColor = false, darkTheme = true) {
                ApplicationScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen(viewModel: ProfileViewModel){
    val profile = viewModel.getCurrentProfile.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Профиль") },
                actions = {
                    IconButton(onClick = {
                        viewModel.shareProfile(context, profile.value)
                    }) {
                        Icon(Icons.Rounded.Share, "Поделиться")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                value = viewModel.username.value,
                onValueChange = { viewModel.updateName(it)},
                placeholder = { Text(text = "Введите имя") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = viewModel.phone.value,
                onValueChange = { viewModel.updatePhone(it) },
                placeholder = { Text(text = "Введите номер телефона") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = { Text(text = "Введите email") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { viewModel.saveCurrentProfile() }) { Text("Сохранить данные") }
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Имя пользователя: ${profile.value.name}")
                    Text(text = "Телефон: ${profile.value.phone}")
                    Text(text = "Email: ${profile.value.email}")
                }
            }
        }
    }
}