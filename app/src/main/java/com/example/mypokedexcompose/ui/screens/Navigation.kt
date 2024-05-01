package com.example.mypokedexcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypokedexcompose.ui.screens.detail.DetailScreen
import com.example.mypokedexcompose.ui.screens.home.HomeScreen
import com.example.mypokedexcompose.data.home.PokedexResult
import com.example.mypokedexcompose.data.home.pokemons


@Composable
fun navigation() {
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