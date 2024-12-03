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
import com.example.mypokedexcompose.data.dataSource.repository.BackPackItemRepository
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.data.region.RegionMapper
import com.example.mypokedexcompose.data.region.RegionRepository
import com.example.mypokedexcompose.framework.GeocoderRegionDataSource
import com.example.mypokedexcompose.framework.PlayServicesLocationDataSource
import com.example.mypokedexcompose.framework.database.backpack.BackPackRoomDataSource
import com.example.mypokedexcompose.framework.database.berries.BerryRoomDataSource
import com.example.mypokedexcompose.framework.database.pokedex.PokedexRoomDataSource
import com.example.mypokedexcompose.framework.database.pokemon.PokemonRoomDataSource
import com.example.mypokedexcompose.framework.mappers.BerryMapper
import com.example.mypokedexcompose.framework.mappers.ItemsMapper
import com.example.mypokedexcompose.framework.mappers.PokemonMapper
import com.example.mypokedexcompose.framework.remote.backpack.BackPackServerDataSource
import com.example.mypokedexcompose.framework.remote.backpack.ItemClient
import com.example.mypokedexcompose.framework.remote.berries.BerryClient
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
import com.example.mypokedexcompose.usecase.FetchBackPackItemByNameUseCase
import com.example.mypokedexcompose.usecase.FetchBackpackItemsUseCase
import com.example.mypokedexcompose.usecase.FetchBerriesUseCase
import com.example.mypokedexcompose.usecase.FetchBerryByNameUseCase
import com.example.mypokedexcompose.usecase.FetchPokedexForRegionUseCase
import com.example.mypokedexcompose.usecase.FetchPokedexUseCase
import com.example.mypokedexcompose.usecase.FetchPokemonByNameUseCase
import com.example.mypokedexcompose.usecase.GetBackPackItemsUseCase
import com.example.mypokedexcompose.usecase.GetBerriesUseCase
import com.example.mypokedexcompose.usecase.GetPokedexUseCase
import com.example.mypokedexcompose.usecase.ToggleFavoriteUseCase
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

    val regionRepository = RegionRepository(
        GeocoderRegionDataSource(
            Geocoder(app),
            PlayServicesLocationDataSource(LocationServices.getFusedLocationProviderClient(app))
        )
    )
    val pokedexRepository =
        PokedexRepository(
            PokedexServerDataSource(PokedexClient, PokemonMapper()),
            PokedexRoomDataSource(app.db.pokedexDao(), PokemonMapper()
            ),
        )
    val backPackItemRepository = BackPackItemRepository(
        BackPackServerDataSource(ItemClient, ItemsMapper()),
        BackPackRoomDataSource(app.db.BackPackDao(), ItemsMapper()
        ),

        )
    val berryRepository = BerryRepository(
        BerryRoomDataSource(
            app.db.berryDao(),
            BerryMapper()
        ),
        BerryServerDataSource(BerryClient, BerryMapper()),
    )
    val pokemonRepository = PokemonRepository(
        PokemonServerDataSource(PokemonClient, PokemonMapper()),
        PokemonRoomDataSource(app.db.pokemonDao(), PokemonMapper()
        ),
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
                        GetPokedexUseCase(pokedexRepository),
                        FetchPokedexUseCase(pokedexRepository),
                        FetchPokedexForRegionUseCase(
                            pokedexRepository
                        ),
                        RegionMapper()
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.Berries.route) {
            BerriesScreen(
                viewModel {
                    BerriesViewModel(
                        GetBerriesUseCase(berryRepository),
                        FetchBerryByNameUseCase(berryRepository),
                        FetchBerriesUseCase(berryRepository)
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
        composable(NavScreen.BackPack.route) {
            BackPackScreen(
                viewModel {
                    BackPackViewModel(
                        GetBackPackItemsUseCase(
                            backPackItemRepository
                        ),
                        FetchBackPackItemByNameUseCase(
                            backPackItemRepository
                        ),
                        FetchBackpackItemsUseCase(
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
                        FetchPokemonByNameUseCase(
                            pokemonRepository
                        ),
                        ToggleFavoriteUseCase(pokemonRepository),
                        pokemonName
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
    }
}