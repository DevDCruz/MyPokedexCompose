package com.example.mypokedexcompose.ui.screens.berries

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypokedexcompose.R
import com.example.mypokedexcompose.data.berries.Berry
import com.example.mypokedexcompose.ui.common.CircularProgressFun
import com.example.mypokedexcompose.ui.common.PropertyDetailItem
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import com.example.mypokedexcompose.ui.screens.Screen
import com.example.mypokedexcompose.ui.theme.DarkRed
import com.example.mypokedexcompose.ui.theme.DarkRedII
import com.example.mypokedexcompose.ui.theme.LightRed
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BerriesScreen(
    vm: BerriesViewModel = viewModel(),
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.onUiReady()
    }

    Screen {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val lazyLisState = rememberLazyListState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Berries",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
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
                    }
                )
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing

        ) { padding ->
            if (state.loading) {
                CircularProgressFun(padding)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkRed),
                    state = lazyLisState,
                    contentPadding = padding
                ) {
                    items(state.berries) { berry ->
                        DropDownBerry(
                            berry = berry,
                            berryId = state.berries.indexOf(berry) + 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DropDownBerry(
    berry: Berry,
    berryId: Int,
    vm: BerriesViewModel = viewModel()
) {
    var selectedText by remember {
        mutableStateOf(
            "$berryId - " + changefirstCharToUpperCase(
                berry.name ?: ""
            )
        )
    }

    var berryDetail by remember { mutableStateOf<Berry?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(LightRed)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    enabled = false,
                    readOnly = true,
                    textStyle = TextStyle(Color.Black),
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                if (berryDetail == null) {
                                    berryDetail = vm.fetchBerryDetails(berry.name ?: "")

                                } else {
                                    berryDetail = null
                                    selectedText =
                                        "$berryId - " + changefirstCharToUpperCase(berry.name ?: "")
                                }
                            }
                        }
                        .fillMaxWidth()
                        .background(LightRed, shape = RoundedCornerShape(8.dp))

                )

                berryDetail?.let { berry ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(buildAnnotatedString {
                            PropertyDetailItem(name = "Name", value = berry.name ?: "")
                            PropertyDetailItem(
                                name = "Firmness",
                                value = berry.firmness?.name ?: ""
                            )
                            PropertyDetailItem(
                                name = "Flavors",
                                value = berry.flavors?.joinToString(", ") { it.flavor.name } ?: "")
                            PropertyDetailItem(
                                name = "Smoothness",
                                value = berry.smoothness?.toString() ?: ""
                            )
                            PropertyDetailItem(name = "ID", value = berry.id?.toString() ?: "")
                            PropertyDetailItem(
                                name = "Size",
                                value = berry.size?.toString() ?: "",
                                true
                            )
                        })
                    }
                }
            }
        }
    }
}
