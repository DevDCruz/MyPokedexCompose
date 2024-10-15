package com.example.mypokedexcompose.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class DetailState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackbarHostState: SnackbarHostState
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun rememberDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): DetailState {
    return remember(scrollBehavior, snackbarHostState) {
        DetailState(
            scrollBehavior,
            snackbarHostState
        )
    }
}