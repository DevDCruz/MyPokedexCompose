package com.example.mypokedexcompose.ui.screens.backpack

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class BackPackState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    val lazyListState: LazyListState
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun remeberBackPackState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    lazyListState: LazyListState = rememberLazyListState()
): BackPackState {
    return remember(scrollBehavior, lazyListState) { BackPackState(scrollBehavior, lazyListState) }
}