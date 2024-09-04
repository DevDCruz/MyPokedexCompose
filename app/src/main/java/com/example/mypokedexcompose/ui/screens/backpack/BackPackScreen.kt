package com.example.mypokedexcompose.ui.screens.backpack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.mypokedexcompose.ui.common.DropDownItem
import com.example.mypokedexcompose.ui.common.PropertyDetailItem
import com.example.mypokedexcompose.ui.screens.ListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackPackScreen(
    vm: BackPackViewModel = viewModel(),
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val backPackState = remeberBackPackState()

    LaunchedEffect(Unit) {
        vm.onUiReady()
    }

    ListScreen(
        title = "BackPack",
        items = state.items,
        onBack = { onBack() },
        loading = state.loading,
        scrollBehavior = backPackState.scrollBehavior,
        lazyListState = backPackState.lazyListState

    ) { item, itemIndex ->
        DropDownBackPack(item, itemIndex)
    }
}

@Composable
fun DropDownBackPack(
    item: Item,
    itemIndex: Int,
    vm: BackPackViewModel = viewModel()
) {
    var itemDetail by remember { mutableStateOf<Item?>(null) }

    DropDownItem(
        title = item.name ?: "",
        index = itemIndex,
        detail = itemDetail,
        onFetchDetails = {
            vm.fetchItemDetails(itemIndex).also { itemDetail = it }
        },
        onClearDetails = { itemDetail = null }
    ) { detailItem ->
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = detailItem.sprites?.default ?: "",
                contentDescription = "item's sprite",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(buildAnnotatedString {
                PropertyDetailItem(name = "Name", value = detailItem.name ?: "")
                PropertyDetailItem(
                    name = "Attributes",
                    value = detailItem.attributes?.joinToString(", ") { it.name } ?: "")
                PropertyDetailItem(name = "Category", value = detailItem.category?.name ?: "")
                PropertyDetailItem(name = "Cost", value = ("${detailItem.cost ?: ""} $").toString(), true)
            }, modifier = Modifier.padding(16.dp))
        }
    }
}