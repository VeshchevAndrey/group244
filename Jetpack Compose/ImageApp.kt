// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
                        title = { Text(text = stringResource(R.string.app_name)) },
                        actions = {
                            Button(onClick = {}) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.other),
                                    contentDescription = "Others actions",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Outlined.MoreVert,
                                    contentDescription = "Others actions",
                                    tint = Color(0xFFBF360C)
                                    )
                            }
                        }
                    )
                }
            ) {
                ResourceFunction(Modifier.padding(it))
            }
        }
    }
}

@Composable
fun ResourceFunction(modifier: Modifier = Modifier){
    val userName = "Андрей"
    val minutes = 55
    val time = if (minutes < 60) "минут" else "часов"
    val unreadMessages = 3

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.welcome),
            fontSize = 20.sp
        )
        Text(
            text = stringResource(R.string.name, userName)
        )
        Text(
            text = stringResource(R.string.last_visit, minutes, time)
        )
        Text(
            text = pluralStringResource(R.plurals.messages,unreadMessages,unreadMessages)
        )
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.dog),
            contentDescription = "THE DOG",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    ResourceFunction()
}
