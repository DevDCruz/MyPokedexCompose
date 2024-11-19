package com.example.mypokedexcompose.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mypokedexcompose.data.Result

@Composable
fun <T> AcScaffold(
    state: com.example.mypokedexcompose.data.Result<T>,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor ),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues, T) -> Unit
) {
    Scaffold (
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets
    ){ padding ->
        when (state) {
            is com.example.mypokedexcompose.data.Result.Success -> content(padding, state.data)
            is com.example.mypokedexcompose.data.Result.Loading -> { CircularProgressFun(padding = padding) }
            is com.example.mypokedexcompose.data.Result.Error -> ErrorText(state.error, modifier)
        }
    }
}

@Composable
fun ErrorText(error: Throwable, modifier: Modifier) {
    Text(text = error.message ?: "Error", modifier = modifier)
}