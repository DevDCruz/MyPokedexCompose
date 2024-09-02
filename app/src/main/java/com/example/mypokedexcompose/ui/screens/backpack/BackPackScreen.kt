package com.example.mypokedexcompose.ui.screens.backpack

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mypokedexcompose.R
import com.example.mypokedexcompose.data.items.Item
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
fun BackPackScreen(
    vm: BackPackViewModel = viewModel(),
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
                            text = "BackPack",
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
                    items(state.items) { item ->
                        DropDownItem(
                            item = item,
                            itemIndex = state.items.indexOf(item) + 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DropDownItem(
    item: Item,
    itemIndex: Int,
    vm: BackPackViewModel = viewModel()
) {
    var selectedText by remember {
        mutableStateOf(
            "$itemIndex - " + changefirstCharToUpperCase(
                item.name ?: ""
            )
        )
    }

    var itemDetail by remember { mutableStateOf<Item?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(LightRed)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    enabled = false,
                    readOnly = true,
                    textStyle = TextStyle(Color.Black),
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                if (itemDetail == null) {
                                    itemDetail = vm.fetchItemDetails(itemIndex)
                                } else {
                                    itemDetail = null
                                    selectedText =
                                        "$itemIndex - " + changefirstCharToUpperCase(
                                            item.name ?: ""
                                        )
                                }
                            }
                        }
                        .fillMaxWidth()
                        .background(LightRed, shape = RoundedCornerShape(8.dp))

                )

                itemDetail?.let { item ->
                    AsyncImage(
                        model = item.sprites?.default ?: "",
                        contentDescription = "item's sprite",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(buildAnnotatedString {
                        PropertyDetailItem(name = "Name", value = item.name ?: "")
                        PropertyDetailItem(
                            name = "Attributes",
                            value = item.attributes?.joinToString(", ") { it.name } ?: "")
                        PropertyDetailItem(name = "Category", value = item.category?.name ?: "")
                        PropertyDetailItem(
                            name = "Cost",
                            value = ("${item.cost ?: ""} $").toString(),
                            true
                        )
                    }, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

