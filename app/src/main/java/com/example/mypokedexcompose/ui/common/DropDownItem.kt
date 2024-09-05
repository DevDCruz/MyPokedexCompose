package com.example.mypokedexcompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.mypokedexcompose.ui.theme.LightRed
import kotlinx.coroutines.launch

@Composable
fun <T> DropDownItem(
    title: String,
    index: Int,
    detail: T?,
    onFetchDetails: suspend () -> T?,
    onClearDetails: () -> Unit,
    displayDetails: @Composable (T) -> Unit
) {
    var selectedText by remember {
        mutableStateOf(index.toString() + " - " + title.changefirstCharToUpperCase())
    }

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
                                if (detail == null) {
                                    onFetchDetails()?.let {
                                        selectedText =
                                            "$index - " + title.changefirstCharToUpperCase()
                                    }
                                } else {
                                    onClearDetails()
                                    selectedText = "$index - " + title.changefirstCharToUpperCase()
                                }
                            }
                        }
                        .fillMaxWidth()
                        .background(LightRed, shape = RoundedCornerShape(8.dp))
                )
                detail?.let { displayDetails(it) }
            }

        }
    }

}