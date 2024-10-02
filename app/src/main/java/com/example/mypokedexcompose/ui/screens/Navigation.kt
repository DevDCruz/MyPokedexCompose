package com.example.mypokedexcompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypokedexcompose.App
import com.example.mypokedexcompose.data.berries.BerryRepository
import com.example.mypokedexcompose.data.dataSource.LocationDataSource
import com.example.mypokedexcompose.data.dataSource.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import com.example.mypokedexcompose.data.dataSource.local.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.local.PokemonLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.backpack.ItemRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRepository
import com.example.mypokedexcompose.data.items.ItemRepository
import com.example.mypokedexcompose.data.pokedex.PokedexRepository
import com.example.mypokedexcompose.data.region.RegionMapper
import com.example.mypokedexcompose.data.region.RegionRepository
import com.example.mypokedexcompose.ui.screens.backpack.BackPackScreen
import com.example.mypokedexcompose.ui.screens.backpack.BackPackViewModel
import com.example.mypokedexcompose.ui.screens.berries.BerriesScreen
import com.example.mypokedexcompose.ui.screens.berries.BerriesViewModel
import com.example.mypokedexcompose.ui.screens.detail.DetailScreen
import com.example.mypokedexcompose.ui.screens.detail.DetailViewModel
import com.example.mypokedexcompose.ui.screens.home.HomeScreen
import com.example.mypokedexcompose.ui.screens.home.HomeViewModel
import com.example.mypokedexcompose.ui.screens.pokedex.PokedexScreen
import com.example.mypokedexcompose.ui.screens.pokedex.PokedexViewModel

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
    val app = LocalContext.current.applicationContext as App
    val regionRepository = RegionRepository(
        RegionDataSource(
            app,
            LocationDataSource(app)
        )
    )
    val pokemonMapper = PokemonMapper()
    val pokedexRepository =
        PokedexRepository(
            PokedexRemoteDataSource(),
            PokedexLocalDataSource(app.db.pokedexDao()),
            pokemonMapper
        )
    val itemRepository = ItemRepository(ItemRemoteDataSource())
    val berryRepository = BerryRepository(BerryRemoteDataSource())
    val pokemonRepository = PokemonRepository(
        PokemonRemoteDataSource(),
        PokemonLocalDataSource(app.db.pokemonDao()),
        pokemonMapper
    )
    val regionMapper = RegionMapper()


    NavHost(navController = navControler, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(
                viewModel {
                    HomeViewModel(pokemonRepository)
                },
                navController = navControler
            )
        }
        composable(NavScreen.Pokedex.route) {
            PokedexScreen(
                onClick = { pokemon ->
                    navControler.navigate(NavScreen.Detail.createRoute(pokemon.name))
                },
                viewModel {
                    PokedexViewModel(
                        SavedStateHandle(),
                        regionRepository,
                        pokedexRepository,
                        regionMapper
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.Berries.route) {
            BerriesScreen(
                viewModel {
                    BerriesViewModel(berryRepository)
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.BackPack.route) {
            BackPackScreen(
                viewModel {
                    BackPackViewModel(itemRepository)
                },
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
                viewModel {
                    DetailViewModel(
                        pokemonRepository,
                        pokemonName
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
    }
}