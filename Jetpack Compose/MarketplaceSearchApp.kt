// package com.example.application244

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val searchState = remember { mutableStateOf(SearchState()) }

            val cars = arrayOf(
                Car(brand = "Volkswagen", model = "Golf 2", cost = 2000, image = R.drawable.golf),
                Car(brand = "BMW", model = "X5", cost = 2800),
                Car(brand = "Toyota", model = "Corolla", cost = 1800),
                Car(brand = "Mercedes-Benz", model = "GLA", cost = 2500),
                Car(brand = "Ford", model = "Focus", cost = 2100),
            )

            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("home") { HomeScreen(
                    cars = cars,
                    navController = navController,
                    currentState = searchState.value
                ) }
                composable("search") { SearchScreen(
                    navController = navController,
                    searchState = searchState,
                ) }
            }
        }
    }
}

@Composable
fun HomeScreen(cars: Array<Car>, navController: NavController, currentState: SearchState){
    val filteredItems = remember {
        derivedStateOf {
            cars.filter { car ->
                val brandMatch = (car.brand.contains(currentState.brandValue, true)) or
                        (currentState.brandValue.isEmpty())
                val modelMatch = (car.model.contains(currentState.modelValue, true)) or
                        (currentState.modelValue.isEmpty())
                val minCost = currentState.minCost.toDoubleOrNull() ?: 0.0
                val maxCost = currentState.maxCost.toDoubleOrNull() ?: Double.MAX_VALUE
                val priceMatch = (car.cost >= minCost) and (car.cost <= maxCost)

                brandMatch and modelMatch and priceMatch
            }
        }
    }

    Scaffold(
        topBar = { TopBar(
            title = "Car list (${filteredItems.value.size})",
            action = {
                IconButton(onClick = {navController.navigate("search")}) {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
                }
            }
        ) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(items = filteredItems.value) { items ->
                SingleItem(items)
            }
        }
    }
}

@Composable
fun SearchScreen(
    navController: NavController,
    searchState: MutableState<SearchState>,
){

    Scaffold(
        topBar = { TopBar(
            title = "Car search",
            navigation = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                }
            }
        ) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchState.value.brandValue,
                onValueChange = { searchState.value = searchState.value.copy(brandValue = it)},
                label = { Text(text = "Brand") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {
                        searchState.value = searchState.value.copy(brandValue = "")
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            )
            OutlinedTextField(
                value = searchState.value.modelValue,
                onValueChange = { searchState.value = searchState.value.copy(modelValue = it) },
                label = { Text(text = "Model") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {
                        searchState.value = searchState.value.copy(modelValue = "")
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = searchState.value.minCost,
                    onValueChange = {searchState.value = searchState.value.copy(minCost = it)},
                    prefix = { Text(text = "cost from") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = searchState.value.maxCost,
                    onValueChange = {searchState.value = searchState.value.copy(maxCost = it)},
                    prefix = { Text(text = "to") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            OutlinedButton(onClick = {
                navController.popBackStack()
            }) { Text("Confirm") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    action: @Composable RowScope.() -> Unit = {},
    navigation : @Composable (() -> Unit) = {}
){
    TopAppBar(
        title = { Text(text = title) },
        actions = action,
        navigationIcon = navigation
    )
}

@Composable
fun SingleItem(item: Car){
    Row(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()) {
        Image(
            bitmap = ImageBitmap.imageResource(item.image),
            contentDescription = "${item.model}'s image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(5.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = item.brand, fontSize = 18.sp)
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = item.model, fontSize = 20.sp)
            }
        }
        Text(text = "${item.cost}$", fontSize = 20.sp)
    }
}

data class SearchState(
    val brandValue: String = "",
    val modelValue: String = "",
    val minCost: String = "",
    val maxCost: String = ""
)

data class Car(
    val brand: String,
    val model: String,
    val cost: Int,
    val image: Int = R.drawable.car_placeholder
)
