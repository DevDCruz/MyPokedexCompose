package com.example.mypokedexcompose.ui.screens

import android.location.Geocoder
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
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.repository.BackPackItemRepository
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.framework.GeocoderRegionDataSource
import com.example.mypokedexcompose.framework.PlayServicesLocationDataSource
import com.example.mypokedexcompose.framework.database.backpack.ItemsMapper
import com.example.mypokedexcompose.framework.remote.backpack.BackPackServerDataSource
import com.example.mypokedexcompose.framework.remote.backpack.ItemClient
import com.example.mypokedexcompose.framework.remote.berries.BerryClient
import com.example.mypokedexcompose.framework.remote.berries.BerryMapper
import com.example.mypokedexcompose.framework.remote.berries.BerryServerDataSource
import com.example.mypokedexcompose.framework.remote.pokedex.PokedexClient
import com.example.mypokedexcompose.framework.remote.pokedex.PokedexServerDataSource
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonClient
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonServerDataSource
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
import com.google.android.gms.location.LocationServices

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

    val regionRepository = com.example.mypokedexcompose.data.region.RegionRepository(
        GeocoderRegionDataSource(
            Geocoder(app),
            PlayServicesLocationDataSource(LocationServices.getFusedLocationProviderClient(app))
        )
    )
    val pokedexRepository =
        PokedexRepository(
            PokedexServerDataSource(PokedexClient),
            com.example.mypokedexcompose.framework.database.pokedex.PokedexRoomDataSource(app.db.pokedexDao()),
            PokemonMapper()
        )
    val backPackItemRepository = BackPackItemRepository(
        BackPackServerDataSource(ItemClient, ItemsMapper()),
        com.example.mypokedexcompose.framework.database.backpack.BackPackRoomDataSource(
            app.db.BackPackDao(),
            ItemsMapper()
        ),

        )
    val berryRepository = BerryRepository(
        com.example.mypokedexcompose.framework.database.berries.BerryRoomDataSource(
            app.db.berryDao(),
            BerryMapper()
        ),
        BerryServerDataSource(BerryClient, BerryMapper()),
        )
    val pokemonRepository = PokemonRepository(
        PokemonServerDataSource(PokemonClient),
        com.example.mypokedexcompose.framework.database.pokemon.PokemonRoomDataSource(app.db.pokemonDao()),
        PokemonMapper()
    )


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
                        com.example.mypokedexcompose.usecase.GetchPokedexUseCase(pokedexRepository),
                        com.example.mypokedexcompose.usecase.FetchPokedexUseCase(pokedexRepository),
                        com.example.mypokedexcompose.usecase.FetchPokedexForRegionUseCase(
                            pokedexRepository
                        ),
                        com.example.mypokedexcompose.data.region.RegionMapper()
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.Berries.route) {
            BerriesScreen(
                viewModel {
                    BerriesViewModel(
                        com.example.mypokedexcompose.usecase.GetchBerriesUseCase(berryRepository),
                        com.example.mypokedexcompose.usecase.FetchBerryByNameUseCase(berryRepository),
                        com.example.mypokedexcompose.usecase.FetchBerriesUseCase(berryRepository)
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.BackPack.route) {
            BackPackScreen(
                viewModel {
                    BackPackViewModel(
                        com.example.mypokedexcompose.usecase.GetBackPackItemsUseCase(
                            backPackItemRepository
                        ),
                        com.example.mypokedexcompose.usecase.FetchBackPackItemByNameUseCase(
                            backPackItemRepository
                        ),
                        com.example.mypokedexcompose.usecase.FetchBackpackItemsUseCase(
                            backPackItemRepository
                        )
                    )
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
                        com.example.mypokedexcompose.usecase.FetchPokemonByNameUseCase(
                            pokemonRepository
                        ),
                        com.example.mypokedexcompose.usecase.ToggleFavoriteUseCase(pokemonRepository),
                        pokemonName
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
    }
}