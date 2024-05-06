package com.example.mypokedexcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypokedexcompose.ui.screens.detail.DetailScreen
import com.example.mypokedexcompose.ui.screens.home.HomeScreen
import com.example.mypokedexcompose.ui.screens.detail.DetailViewModel


@Composable
fun navigation() {
    val navControler = rememberNavController()
    NavHost(navController = navControler, startDestination = "home") {
        composable("home") {
            HomeScreen(onClick = { pokemon ->
                navControler.navigate("detail/${pokemon.name}")
            })
        }
        composable(
            "detail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName = requireNotNull(backStackEntry.arguments?.getString("pokemonName"))
            DetailScreen(
                viewModel { DetailViewModel(pokemonName) },
                onBack = { navControler.popBackStack() }
            )
        }
    }
}