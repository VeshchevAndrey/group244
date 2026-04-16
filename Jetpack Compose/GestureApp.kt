// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen()
        }
    }
}

@Composable
fun Screen(){
    Column() {
        val textChange = remember { mutableStateOf("Данная строка кликабельна!") }
        val tapType = remember { mutableStateOf("Вы ещё не нажали на меня!") }

        Row(
            modifier = Modifier.fillMaxWidth().clickable(
                onClick = {textChange.value = "Ура! Текст сменился!"}
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.golf),
                contentDescription = "Image",
                modifier = Modifier.size(150.dp)
            )
            Text(text = textChange.value)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit){
                    detectTapGestures(
                        onTap = { tapType.value = "Вы дотронулись до объекта" },
                        onPress = { tapType.value = "Вы нажали на объект" },
                        onLongPress = { tapType.value = "Вы зажали объект" },
                        onDoubleTap = { tapType.value = "Вы дважды нажали на объект!" }
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Image",
                modifier = Modifier.size(150.dp)
            )
            Text(text = tapType.value)
        }
    }
}
