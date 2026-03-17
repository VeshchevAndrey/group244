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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
fun MyDropdownMenu(){
    val menuState = remember { mutableStateOf(false) }
    val dialogState = remember { mutableStateOf(false) }

    IconButton(onClick = {menuState.value = true}) {
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "More"
        )
    }
    DropdownMenu(
        expanded = menuState.value,
        onDismissRequest = {menuState.value = false}
    ) {
        DropdownMenuItem(
            text = { Text(text = "Сброс") },
            onClick = {},
            leadingIcon = { Icon(Icons.Rounded.Refresh, "Refresh") }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text(text = "О приложении") },
            onClick = { dialogState.value = true },
            leadingIcon = { Icon(Icons.Rounded.Info, "Info") }
        )
        if (dialogState.value){
            BasicAlertDialog(
                onDismissRequest = { dialogState.value = false }
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.size(300.dp)
                        .background(
                            Color(0xFFFFFFFF), RoundedCornerShape(5.dp)
                        )
                ) {
                    Text(text = "Приложение создал я!")
                    Button(
                        onClick = { dialogState.value = false }
                    ) { Text(text = "Спасибо") }
                }
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
            MyDropdownMenu()
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
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8EAF6)),
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
