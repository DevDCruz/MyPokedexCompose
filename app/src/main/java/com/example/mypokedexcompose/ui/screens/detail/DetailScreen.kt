package com.example.mypokedexcompose.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.ui.common.CircularProgressFun
import com.example.mypokedexcompose.ui.common.PropertyPokemonDetail
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import com.example.mypokedexcompose.ui.screens.Screen
import com.example.mypokedexcompose.ui.theme.DarkRed
import com.example.mypokedexcompose.ui.theme.DarkRedII
import com.example.mypokedexcompose.ui.theme.LightRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = changefirstCharToUpperCase(state.pokemon?.name ?: ""),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = DarkRed,
                        scrolledContainerColor = DarkRedII
                    ),
                    scrollBehavior = scrollBehavior,
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
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .background(DarkRed),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding ->

            if (state.loading) {
                CircularProgressFun(padding)
            } else {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = padding.calculateTopPadding())
                        .verticalScroll(rememberScrollState())
                        .background(DarkRed)
                ) {

                    state.pokemon?.let { pokemon ->

                        DeatilPokemonItem(pokemon)

                    } ?: kotlin.run {
                        Text(text = "Pokemon not found")
                    }
                }
            }
        }
    }
}

@Composable
fun DeatilPokemonItem(pokemon: Pokemon) {

    Column {
        AsyncImage(
            model = "${stringResource(id = R.string.sprite_officialArtoWork_URL)}${pokemon.id}.png",
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
                    PropertyPokemonDetail(name = "Weight", value = pokemon.weight.toString(), true)
                },
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}


