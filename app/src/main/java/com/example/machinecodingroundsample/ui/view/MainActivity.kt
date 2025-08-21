package com.example.machinecodingroundsample.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.machinecodingroundsample.data.model.CatBreedResponse
import com.example.machinecodingroundsample.ui.base.UiState
import com.example.machinecodingroundsample.ui.theme.MachineCodingRoundSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewmodel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainViewmodel.fetchCatBreeds()

        setContent {
            MachineCodingRoundSampleTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("dashboard") {
                            val uiState = mainViewmodel.uiState.collectAsState()
                            DashBoardScreen(
                                uiState = uiState.value,
                                onRetry = { mainViewmodel.fetchCatBreeds() },
                                onItemClick = { breed ->
                                    navController.navigate("detail/${breed.id}")
                                }
                            )
                        }

                        composable(
                            route = "detail/{breedId}",
                            arguments = listOf(navArgument("breedId") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val breedId = backStackEntry.arguments?.getString("breedId") ?: ""
                            CatBreedDetailScreen(breedId = breedId)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun DashBoardScreen(
    uiState: UiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    onItemClick: (CatBreedResponse) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(uiState.catBreedList) { breed ->
                    CatBreedCard(breed, onClick = { onItemClick(breed) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        is UiState.Error -> {
            Box(modifier = modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = uiState.errorMessage, color = Color.Red)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onRetry) { Text("Retry") }
                }
            }
        }
    }
}

@Composable
fun CatBreedCard(breed: CatBreedResponse, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = breed.name, style = MaterialTheme.typography.titleLarge)
            breed.origin?.let { Text("Origin: $it", style = MaterialTheme.typography.bodyMedium) }
        }
    }
}

@Composable
fun CatBreedDetailScreen(breedId: String) {
    val viewModel: MainViewmodel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is UiState.Success -> {
            val breed = state.catBreedList.find { it.id == breedId }
            breed?.let { catBreed ->
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Text(text = catBreed.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    catBreed.origin?.let { Text("Origin: $it", style = MaterialTheme.typography.bodyMedium) }
                    catBreed.description?.let { Text("Description: $it", style = MaterialTheme.typography.bodyMedium) }
                    catBreed.temperament?.let { Text("Temperament: $it", style = MaterialTheme.typography.bodyMedium) }
                    catBreed.life_span?.let { Text("Life Span: $it years", style = MaterialTheme.typography.bodyMedium) }
                }
            } ?: Text("Breed not found")
        }

        is UiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Something Went Wrong!!!")
        }
    }
}
