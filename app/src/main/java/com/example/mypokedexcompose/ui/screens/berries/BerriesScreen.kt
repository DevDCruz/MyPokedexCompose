package com.example.mypokedexcompose.ui.screens.berries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypokedexcompose.domaindatalayer.berries.Berry
import com.example.mypokedexcompose.ui.common.AcScaffold
import com.example.mypokedexcompose.ui.common.DropDownCustomItem
import com.example.mypokedexcompose.ui.common.PropertyDetailItem
import com.example.mypokedexcompose.ui.theme.DarkRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BerriesScreen(
    vm: BerriesViewModel = viewModel(),
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val berriesState = remeberBerriesState()


    AcScaffold(
        state = state,
        topBar = {
            TopAppBar(
                title = { Text("Berries") },
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
                scrollBehavior = berriesState.scrollBehavior
            )
        },
        containerColor = DarkRed
    ) { padding, berries ->
        LazyColumn(
            state = berriesState.lazyListState,
            contentPadding = padding
        ) {
            itemsIndexed(berries) { index, berry ->
                DropDownBerry(berry, index +1)
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
    var berryDetail by remember { mutableStateOf<Berry?>(null) }

    DropDownCustomItem(
        title = berry.name ?: "",
        index = berryId,
        detail = berryDetail,
        onFetchDetails = {
            vm.fetchBerryDetails(berry.name ?: "").also { berryDetail = it }
        },
        onClearDetails = { berryDetail = null }

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(buildAnnotatedString {
                PropertyDetailItem(name = "Name", value = berryDetail?.name ?: "")
                PropertyDetailItem(name = "Firmness", value = berryDetail?.firmness?.name ?: "")
                PropertyDetailItem(
                    name = "Flavors",
                    value = berryDetail?.flavors?.joinToString(", ") { it.flavor.name } ?: ""
                )
                PropertyDetailItem(
                    name = "Smoothness",
                    value = berryDetail?.smoothness?.toString() ?: ""
                )
                PropertyDetailItem(name = "ID", value = berryDetail?.id?.toString() ?: "")
                PropertyDetailItem(
                    name = "Size",
                    value = berryDetail?.size?.toString() ?: "",
                    true
                )
            })
        }
    }
}

