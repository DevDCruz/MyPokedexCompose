package com.example.mypokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mypokedexcompose.ui.screens.Navigation
import com.example.mypokedexcompose.ui.theme.MyPokedexComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }
    }
}


@Preview
@Composable
private fun MyLazyColumnPreview() {
    MyPokedexComposeTheme {
        Surface {

        }
    }
}