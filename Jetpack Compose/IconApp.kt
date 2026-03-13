// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview


// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            val choicedOption = remember { mutableStateOf("") }
            Scaffold(
                topBar = { MyTopBar() },
                bottomBar = { MyBottomBar(choicedOption) }
            ) {
                Application5(modifier = Modifier.padding(it), choicedOption)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(){
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.share),
                    contentDescription = "Share"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "More"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFBBDEFB),
            actionIconContentColor = Color(0xFF3F51B5)
        )
    )
}

@Composable
fun MyBottomBar(option: MutableState<String>){
    val iconsArray = arrayOf(Icons.Rounded.Home, Icons.Rounded.Phone, Icons.Rounded.Favorite)
    Row(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFE8EAF6)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        iconsArray.forEach { icon ->
            IconButton(onClick = {option.value = icon.name}) {
                Icon(
                    imageVector = icon,
                    contentDescription = icon.name
                )
            }
        }
    }
}

@Composable
fun Application5(modifier: Modifier = Modifier, text: MutableState<String>){
    Column(modifier = modifier) {
        Text(text = text.value)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){

}
