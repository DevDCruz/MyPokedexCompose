package com.example.mypokedexcompose.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun AnnotatedString.Builder.PropertyPokemonDetail(name: String, value: String, end: Boolean= false) {
    withStyle(ParagraphStyle(lineHeight = 24.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!end) append("\n")
    }
}

@Composable
fun AnnotatedString.Builder.PropertyDetailItem(name: String, value: String, end: Boolean = false) {
    withStyle(ParagraphStyle(lineHeight = 16.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(changefirstCharToUpperCase(value))
        if (!end) append("\n")
    }
}