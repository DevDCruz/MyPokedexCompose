package com.example.mypokedexcompose.ui.screens.berries

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class BerriesState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    val lazyListState: LazyListState
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun remeberBerriesState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    lazyListState: LazyListState = rememberLazyListState()
): BerriesState {
    return remember(scrollBehavior, lazyListState) { BerriesState(scrollBehavior, lazyListState)  }
}