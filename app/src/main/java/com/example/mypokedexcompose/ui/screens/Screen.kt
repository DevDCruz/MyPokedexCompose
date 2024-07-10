package com.example.mypokedexcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mypokedexcompose.ui.theme.DarkRed
import com.example.mypokedexcompose.ui.theme.MyPokedexComposeTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    MyPokedexComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkRed),
            content = content
        )
    }

}