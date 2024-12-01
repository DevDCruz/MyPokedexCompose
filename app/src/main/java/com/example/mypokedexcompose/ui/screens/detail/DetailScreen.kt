package com.example.mypokedexcompose.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mypokedexcompose.R
import com.example.mypokedexcompose.data.ifSuccess
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.ui.common.AcScaffold
import com.example.mypokedexcompose.ui.common.Constants
import com.example.mypokedexcompose.ui.common.PropertyPokemonDetail
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import com.example.mypokedexcompose.ui.theme.DarkRed
import com.example.mypokedexcompose.ui.theme.DarkRedII
import com.example.mypokedexcompose.ui.theme.LightRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val detailState = rememberDetailState()

    AcScaffold(
        state = state,
        topBar = {
            TopAppBar(
                title = {
                    val pokemonName = when (state) {
                        is com.example.mypokedexcompose.data.Result.Success<PokemonDomain> -> (state as com.example.mypokedexcompose.data.Result.Success<PokemonDomain>).data.name
                        else -> ""
                    }
                    Text(
                        text = pokemonName.changefirstCharToUpperCase(),
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkRed,
                    scrolledContainerColor = DarkRedII
                ),
                scrollBehavior = detailState.scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
            )
        },
        floatingActionButton = {

            val favorite = when (state) {
                is com.example.mypokedexcompose.data.Result.Success<PokemonDomain> -> (state as com.example.mypokedexcompose.data.Result.Success<PokemonDomain>).data.favorite
                else -> false
            }

            FloatingActionButton(
                onClick = { vm.onFavoriteClicked() },
                containerColor = DarkRedII
            ) {
                Icon(
                    imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite),
                    modifier = Modifier.background(color = DarkRedII)
                )
            }
        },
        containerColor = DarkRed,
        snackbarHost = { SnackbarHost(hostState = detailState.snackbarHostState) },
        modifier = Modifier
            .nestedScroll(detailState.scrollBehavior.nestedScrollConnection)
            .background(DarkRed),
        contentWindowInsets = WindowInsets.safeDrawing
    ) { padding, pokemon ->
        LazyColumn(
            contentPadding = padding
        ) {
           state.ifSuccess {
               item { DeatilPokemonItem(pokemon) }
           }
        }
    }
}

@Composable
fun DeatilPokemonItem(pokemon: PokemonDomain) {
    Column {
        AsyncImage(
            model = "${Constants.SPRITE_OFFICIAL_ARTWORK_URL}${pokemon.id}.png",
            contentDescription = pokemon.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                .background(LightRed, shape = RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                .background(LightRed, shape = RoundedCornerShape(16.dp))
        ) {
            Text(
                text = buildAnnotatedString {
                    PropertyPokemonDetail(name = "Type", value = getPokemonType(pokemon))
                    PropertyPokemonDetail(name = "Nº Pokedex", value = pokemon.id.toString())
                    PropertyPokemonDetail(name = "Height", value = pokemon.height.toString())
                    PropertyPokemonDetail(
                        name = "Weight",
                        value = pokemon.weight.toString(),
                        true
                    )
                },
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}





