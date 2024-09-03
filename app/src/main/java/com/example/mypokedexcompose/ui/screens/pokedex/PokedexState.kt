package com.example.mypokedexcompose.ui.screens.pokedex

import android.Manifest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.mypokedexcompose.ui.common.PermissionRequestEffect
import com.example.mypokedexcompose.ui.common.getRegion
import kotlinx.coroutines.launch

class PokedexHomeState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior
) {

    @Composable
    fun AskRegionEffect(onRegion: (String) -> Unit) {
        val ctx = LocalContext.current.applicationContext
        val coroutineScope = rememberCoroutineScope()

        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
            coroutineScope.launch {
                val region = ctx.getRegion()
                onRegion(region)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberPokedexState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
): PokedexHomeState {
    return remember { PokedexHomeState(scrollBehavior) }
}