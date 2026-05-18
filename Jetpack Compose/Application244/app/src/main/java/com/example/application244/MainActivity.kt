package com.example.application244

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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.application244.ui.theme.Application244Theme

// Точка сборки и запуска окна мобильного приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Application244Theme() {
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
            TopAppBar(title = { Text(text = "Бестиарий") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(10.dp)
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
    ) { Text(text = creature.name) }
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
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Image(
                ImageBitmap.imageResource(creature.image),
                contentDescription = creature.name,
                modifier = Modifier
                    .size(250.dp)
            )
            Text(text = creature.description)
        }
    }
}