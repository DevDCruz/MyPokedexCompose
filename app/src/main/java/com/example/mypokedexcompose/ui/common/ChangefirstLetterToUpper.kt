package com.example.mypokedexcompose.ui.common

import java.util.Locale

fun String.changefirstCharToUpperCase(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}