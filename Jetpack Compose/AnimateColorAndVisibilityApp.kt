// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.application244.ui.theme.Application244Theme

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application244Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

// animateColorAsState - анимирует изменение цвета элемента. Принимает объект типа Color
// tween - управляет воспроизведением анимации (скорость, тип анимации, задержка и т.п.)
// keyframes - управляет ключевыми кадрами анимации, позволяя задавать промежуточные значения

@Composable
fun ApplicationScreen(modifier: Modifier){
    val startColor = Color.LightGray
    val endColor = Color.Yellow

    val boxColor = remember { mutableStateOf(startColor) }

    val colorAnimation = animateColorAsState(
        targetValue = boxColor.value,
        animationSpec = tween(durationMillis = 1000)
    )
    val colorAnimationExtended = animateColorAsState(
        targetValue = boxColor.value,
        animationSpec = keyframes {
            durationMillis = 2000
            Color(0xFFFFF8E1) at 500
            Color(0xFFFFE082) at 1000
            Color(0xFFFFCA28) at 1500
        }
    )

    val imageVisible = remember { mutableStateOf(true) }

    val newBoxColor = remember { mutableStateOf(Color.LightGray) }
    val newBoxColorExtended = animateColorAsState(targetValue = newBoxColor.value)

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(newBoxColorExtended.value)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { newBoxColor.value = Color.LightGray },
                        onPress = { },
                        onLongPress = {
                            newBoxColor.value = Color.White
                        }
                    )
                }
        )

        Box(modifier = Modifier.size(150.dp).background(colorAnimation.value))
        Button(onClick = {
            boxColor.value = if (boxColor.value == startColor) endColor else startColor
        }) { Text(text = "Изменить цвет") }

        Box(modifier = Modifier.size(150.dp).background(colorAnimationExtended.value))
        Button(onClick = {
            boxColor.value = if (boxColor.value == startColor) endColor else startColor
        }) { Text(text = "Изменить цвет") }

        AnimatedVisibility(
            visible = imageVisible.value,
            exit = scaleOut() + shrinkHorizontally(),
            enter = scaleIn() + expandHorizontally()
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.placeholder),
                contentDescription = "Placeholder",
                modifier = Modifier.size(150.dp)
            )
        }
        Button(onClick = {
            imageVisible.value = !imageVisible.value
        }) { Text(text = "Изменить видимость") }
    }
}
