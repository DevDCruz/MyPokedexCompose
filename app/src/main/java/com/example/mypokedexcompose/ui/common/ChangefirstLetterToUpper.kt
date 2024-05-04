package com.example.mypokedexcompose.ui.common

import java.util.Locale

fun changefirstCharToUpperCase(str: String): String {
    return str.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}