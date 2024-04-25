package com.example.mypokedexcompose.ui.screens.home

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.ui.theme.MyPokedexComposeTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    MyPokedexComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Screen {
        var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val pokemon = Pokemon("Bulbasaur", 1, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
        Scaffold(
            topBar =  {
                TopAppBar(
                    title = { Text(text = "Pokedex") },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = padding
            ) {
                items(151) { index ->
                    Text(
                        text = "$index ${pokemon.name}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }


}
