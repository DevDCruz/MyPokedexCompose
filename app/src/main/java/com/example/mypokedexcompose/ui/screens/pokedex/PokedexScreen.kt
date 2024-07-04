package com.example.mypokedexcompose.ui.screens.pokedex


import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mypokedexcompose.R
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.ui.common.CircularProgressFun
import com.example.mypokedexcompose.ui.common.PermissionRequestEffect
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import com.example.mypokedexcompose.ui.common.getRegion
import com.example.mypokedexcompose.ui.screens.Screen
import com.example.mypokedexcompose.ui.theme.DarkRed
import com.example.mypokedexcompose.ui.theme.DarkRedII
import com.example.mypokedexcompose.ui.theme.LightRed
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen(
    onClick: (Pokemon) -> Unit,
    vm: PokedexViewModel = viewModel(),
    onBack: () -> Unit
) {

    val ctx = LocalContext.current.applicationContext
    val appName = stringResource(id = R.string.app_name)
    var appBarTitle by remember { mutableStateOf(appName) }
    val coroutineScope = rememberCoroutineScope()
    val state by vm.state.collectAsState()




    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
        if (granted) {
            coroutineScope.launch {
                val region = ctx.getRegion()
                appBarTitle = "$appName - ($region)"
            }
        } else {
            appBarTitle = "$appName (Permission Denied)"
        }
        vm.onUiReady()
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val lazyLisState = rememberLazyListState(
            initialFirstVisibleItemIndex = vm.state.collectAsState().value.scrollPosition
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = appBarTitle) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = DarkRed,
                        scrolledContainerColor = DarkRedII
                    ),
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    },
                )
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,


            ) { padding ->

            if (state.loading) {
                CircularProgressFun(padding = padding)
            } else {

                LazyColumn(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkRed),
                    state = lazyLisState,
                    contentPadding = padding
                ) {
                    items(state.pokemons) { pokemon ->
                        vm.state.collectAsState().value.spritePokedex?.let {
                            PokedexItem(
                                pokemon = pokemon,
                                onClick = {
                                    onClick(pokemon)
                                    vm.savedScrollPosition(lazyLisState.firstVisibleItemIndex)
                                },
                                pokedexNumber = state.pokemons.indexOf(pokemon) + 1,
                                sprite = it
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokedexItem(pokemon: Pokemon, onClick: () -> Unit, pokedexNumber: Int, sprite: String) {

    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .background(LightRed, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "$sprite$pokedexNumber.png",
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(60.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = "$pokedexNumber - ",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = changefirstCharToUpperCase(pokemon.name),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        )
    }
}