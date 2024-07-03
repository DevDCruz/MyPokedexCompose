package com.example.mypokedexcompose.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mypokedexcompose.R
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.ui.common.CircularProgressFun
import com.example.mypokedexcompose.ui.screens.pokedex.PokedexScreen
import com.example.mypokedexcompose.ui.theme.DarkRed
import com.example.mypokedexcompose.ui.theme.DarkRedII
import com.example.mypokedexcompose.ui.theme.LightRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(vm: HomeViewModel) {
    val state by vm.state.collectAsState()

    PokedexScreen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "PokeApp")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = DarkRed,
                        scrolledContainerColor = DarkRedII
                    ),
                )
            }
        ) { padding ->
            if (state.loading) {
                CircularProgressFun(padding)
            } else {
                state.pokemon?.let { pokemon ->
                    HomeScreenItem(pokemon, state.sprite ?: "")

                }
            }
        }

    }
}


@Composable
fun HomeScreenItem(pokemon: Pokemon, sprite: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkRed)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "${sprite}${pokemon.id}.png",
            contentDescription = pokemon.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                .background(LightRed, shape = RoundedCornerShape(16.dp))
                .size(400.dp)

        )

        IconButtonWithText(
            imageRes = R.drawable.ic_pokedex,
            text = "Pokedex",
            backgroundColor = LightRed
        ) {

        }
        IconButtonWithText(
            imageRes = R.drawable.ic_bag,
            text = "Bag",
            backgroundColor = LightRed) {

        }
        IconButtonWithText(
            imageRes = R.drawable.ic_berry,
            text = "Berries",
            backgroundColor = LightRed
        ) {

        }
    }
}

@Composable
fun IconButtonWithText(
    imageRes: Int,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.Black, shape = MaterialTheme.shapes.large)
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .size(width = 250.dp, height = 50.dp),


    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp
            )
        }
    }
}