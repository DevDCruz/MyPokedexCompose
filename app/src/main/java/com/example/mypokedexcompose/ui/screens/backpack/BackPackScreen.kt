package com.example.mypokedexcompose.ui.screens.backpack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mypokedexcompose.data.items.Item
import com.example.mypokedexcompose.ui.common.AcScaffold
import com.example.mypokedexcompose.ui.common.DropDownCustomItem
import com.example.mypokedexcompose.ui.common.PropertyDetailItem
import com.example.mypokedexcompose.ui.theme.DarkRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackPackScreen(
    vm: BackPackViewModel = viewModel(),
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val backPackState = remeberBackPackState()

    AcScaffold(
        state = state,
        topBar = {
            TopAppBar(
                title = { Text("BackPack") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = DarkRed,
                    scrolledContainerColor = DarkRed
                ),
                scrollBehavior = backPackState.scrollBehavior
            )
        },
        containerColor = DarkRed
    ) { padding, items ->
        LazyColumn(
            state = backPackState.lazyListState,
            contentPadding = padding
        ) {
            itemsIndexed(items) { index, item ->
                DropDownBackPack(item = item, itemIndex = index + 1)
            }
        }
    }
}

@Composable
fun DropDownBackPack(
    item: Item,
    itemIndex: Int,
    vm: BackPackViewModel = viewModel()
) {
    var itemDetail by remember { mutableStateOf<Item?>(null) }

    DropDownCustomItem(
        title = item.name ?: "",
        index = itemIndex,
        detail = itemDetail,
        onFetchDetails = {
            vm.fetchItemDetails(item.name ?: "").also { itemDetail = it }
        },
        onClearDetails = { itemDetail = null }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = itemDetail?.sprites?.default ?: "",
                contentDescription = "item's sprite",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(buildAnnotatedString {
                PropertyDetailItem(name = "Name", value = itemDetail?.name ?: "")
                PropertyDetailItem(
                    name = "Attributes",
                    value = itemDetail?.attributes?.joinToString(", ") { it.name } ?: "")
                PropertyDetailItem(name = "Category", value = itemDetail?.category?.name ?: "")
                PropertyDetailItem(
                    name = "Cost",
                    value = ("${itemDetail?.cost ?: ""} $").toString(),
                    true
                )
            }, modifier = Modifier.padding(16.dp))
        }
    }
}