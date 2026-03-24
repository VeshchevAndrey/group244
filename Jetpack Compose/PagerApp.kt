// package com.example.application244 Здесь название Вашего приложения!

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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

val characters = arrayOf(
    Character(name = "Cloud Strife", image = R.drawable.img1),
    Character(name = "Tifa Lockhart", image = R.drawable.img2),
    Character(name = "Aerith Gainsborough", image = R.drawable.img3),
    Character(name = "Barret Wallace", image = R.drawable.img4)
)


@Composable
fun PagerFunction(){
    val pagerState = rememberPagerState() { characters.size }

    HorizontalPager (
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(characters[it].image),
                contentDescription = characters[it].name,
                modifier = Modifier.size(200.dp)
            )
            Text(
                characters[it].name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMyFunctions(){
    PagerFunction()
}

data class Character(val name: String, val image: Int)
