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
import com.example.mypokedexcompose.data.dataSource.GeocoderRegionDataSource
import com.example.mypokedexcompose.data.dataSource.PlayServicesLocationDataSource
import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackRoomDataSource
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryRoomDataSource
import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexRoomDataSource
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonRoomDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.BerryMapper
import com.example.mypokedexcompose.data.dataSource.mappers.ItemsMapper
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.backpack.ItemClient
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryClient
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexClient
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexServerDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonClient
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.repository.BackPpackItemRepository
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
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
import com.example.mypokedexcompose.usecases.FetchBackPackItemByNameUseCase
import com.example.mypokedexcompose.usecases.FetchBackpackItemsUseCase
import com.example.mypokedexcompose.usecases.FetchBerriesUseCase
import com.example.mypokedexcompose.usecases.FetchBerryByNameUseCase
import com.example.mypokedexcompose.usecases.FetchPokedexForRegionUseCase
import com.example.mypokedexcompose.usecases.FetchPokedexUseCase
import com.example.mypokedexcompose.usecases.FetchPokemonByNameUseCase
import com.example.mypokedexcompose.usecases.GetBackPackItemsUseCase
import com.example.mypokedexcompose.usecases.GetchBerriesUseCase
import com.example.mypokedexcompose.usecases.GetchPokedexUseCase
import com.example.mypokedexcompose.usecases.ToggleFavoriteUseCase
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
            PokedexServerDataSource(PokedexClient),
            PokedexRoomDataSource(app.db.pokedexDao()),
            PokemonMapper()
        )
    val backPpackItemRepository = BackPpackItemRepository(
        BackPackRemoteDataSource(ItemClient),
        BackPackRoomDataSource(app.db.BackPackDao()),
        ItemsMapper()
    )
    val berryRepository = BerryRepository(
        BerryRemoteDataSource(BerryClient),
        BerryRoomDataSource(app.db.berryDao()),
        BerryMapper()
    )
    val pokemonRepository = PokemonRepository(
        PokemonRemoteDataSource(PokemonClient),
        PokemonRoomDataSource(app.db.pokemonDao()),
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
                        GetchPokedexUseCase(pokedexRepository),
                        FetchPokedexUseCase(pokedexRepository),
                        FetchPokedexForRegionUseCase(pokedexRepository),
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
                        GetchBerriesUseCase(berryRepository),
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
                        GetBackPackItemsUseCase(backPpackItemRepository),
                        FetchBackPackItemByNameUseCase(backPpackItemRepository),
                        FetchBackpackItemsUseCase(backPpackItemRepository)
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
                        FetchPokemonByNameUseCase(pokemonRepository),
                        ToggleFavoriteUseCase(pokemonRepository),
                        pokemonName
                    )
                },
                onBack = { navControler.popBackStack() }
            )
        }
    }
}