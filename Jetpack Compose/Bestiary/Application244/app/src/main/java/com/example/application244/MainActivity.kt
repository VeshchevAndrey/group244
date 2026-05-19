package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.application244.ui.theme.Application244Theme
import com.example.application244.ui.theme.gradient

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application244Theme(dynamicColor = false, darkTheme = true) {
                ApplicationScreen()
            }
        }
    }
}

@Composable
fun ApplicationScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "names_screen"){
        composable(route = "names_screen") {
            CreatureListScreen(navController = navController)
        }
        composable(
            route = "details_screen/{creatureId}",
            arguments = listOf(navArgument("creatureId") { type = NavType.IntType })
            ) { backStackEntry ->
            val creatureId = backStackEntry.arguments?.getInt("creatureId") ?: 0
            CreatureDetailScreen(navController = navController, creatureId = creatureId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatureListScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Бестиарий") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0147A1),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black,
        contentColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(items = CreaturesRepository.creatures){ creature ->
                CreatureListItem(creature = creature) {
                    navController.navigate("details_screen/${creature.id}")
                }
            }
        }
    }
}

@Composable
fun CreatureListItem(creature: Creature, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .background(brush = gradient, shape = RoundedCornerShape(5.dp))
            .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(5.dp))
            .padding(15.dp)
    ) { Text(
        text = creature.name,
        style = TextStyle(shadow = Shadow(Color.Black, Offset(3f, 3f), 3f))
    )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatureDetailScreen(navController: NavController, creatureId: Int){
    val creature = CreaturesRepository.getCreatureById(creatureId)

    if (creature == null) {
        Scaffold { paddingValues ->
            Text(text = "Существо не найдено!", modifier = Modifier.padding(paddingValues))
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = creature.name) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0147A1),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black,
        contentColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = gradient, shape = RoundedCornerShape(5.dp))
                    .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(5.dp))
            ){
                Image(
                    ImageBitmap.imageResource(R.drawable.battlebg_ffvii_forest),
                    contentDescription = "Задний фон",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.Center),
                    contentScale = ContentScale.Crop
                )
                Image(
                    ImageBitmap.imageResource(creature.image),
                    contentDescription = creature.name,
                    modifier = Modifier
                        .size(250.dp)
                        .align(alignment = Alignment.Center)
                        .padding(15.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = gradient, shape = RoundedCornerShape(5.dp))
                    .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(5.dp))
                    .padding(15.dp)
            ){
                Text(text = creature.description)
            }

        }
    }
}