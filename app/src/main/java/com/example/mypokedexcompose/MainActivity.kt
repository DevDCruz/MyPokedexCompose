package com.example.mypokedexcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.pokemons
import com.example.mypokedexcompose.ui.screens.detail.DetailScreen
import com.example.mypokedexcompose.ui.screens.home.HomeScreen
import com.example.mypokedexcompose.ui.theme.MyPokedexComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navControler = rememberNavController()
            NavHost(navController = navControler, startDestination = "home") {
                composable("home") {
                    HomeScreen(onClick = { pokemon ->
                        navControler.navigate("detail/${pokemon.id}")
                    })
                }
                composable(
                    "detail/{pokemonId}",
                    arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
                    DetailScreen(
                        pokemon = pokemons.first { it.id == pokemonId },
                        onBack = { navControler.popBackStack() }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun MyLazyColumnPreview() {
    MyPokedexComposeTheme {
        Surface {

        }
    }
}