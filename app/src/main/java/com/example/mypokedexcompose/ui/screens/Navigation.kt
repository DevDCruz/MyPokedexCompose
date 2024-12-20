package com.example.mypokedexcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypokedexcompose.ui.screens.backpack.BackPackScreen
import com.example.mypokedexcompose.ui.screens.berries.BerriesScreen
import com.example.mypokedexcompose.ui.screens.detail.DetailScreen
import com.example.mypokedexcompose.ui.screens.home.HomeScreen
import com.example.mypokedexcompose.ui.screens.pokedex.PokedexScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class NavScreen(val route: String) {
    data object Home : NavScreen("home")
    data object Detail : NavScreen("detail/{${NavArs.POKEMON_NAME.key}}") {
        fun createRoute(pokemonName: String) = "detail/$pokemonName"
    }

    data object Pokedex : NavScreen("pokedex")
    data object BackPack : NavScreen("backpack")
    data object Berries : NavScreen("berries")
}

enum class NavArs(val key: String) {
    POKEMON_NAME("pokemonName")
}

@Composable
fun Navigation() {
    val navControler = rememberNavController()


    NavHost(navController = navControler, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(
                navController = navControler
            )
        }
        composable(NavScreen.Pokedex.route) {
            PokedexScreen(
                onClick = { pokemon ->
                    navControler.navigate(NavScreen.Detail.createRoute(pokemon.name))
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.Berries.route) {
            BerriesScreen(
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.BackPack.route) {
            BackPackScreen(
                onBack = { navControler.popBackStack() }
            )
        }

        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArs.POKEMON_NAME.key) { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName =
                requireNotNull(backStackEntry.arguments?.getString(NavArs.POKEMON_NAME.key))
            DetailScreen(
               koinViewModel(parameters = { parametersOf(pokemonName) }),
                onBack = { navControler.popBackStack() }
            )
        }
    }
}