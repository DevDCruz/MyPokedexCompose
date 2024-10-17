package com.example.mypokedexcompose.data.region

enum class PokedexRegion(val displayName: String, val range: IntArray) {
    GENERATION_1("Kanto", intArrayOf(1, 151)),
    GENERATION_2("Johto", intArrayOf(152, 251)),
    GENERATION_3("Hoenn", intArrayOf(252, 386)),
    GENERATION_4("Sinnoh", intArrayOf(387, 494)),
    GENERATION_5("Unova", intArrayOf(495, 649)),
    GENERATION_6("Kalos", intArrayOf(650, 721)),
    GENERATION_7("Alola", intArrayOf(722, 809)),
    GENERATION_8("Galar", intArrayOf(810, 905)),
    GENERATION_9("Paldea", intArrayOf(906, 1025)),
    ESPECIAL_FORMS("Forms", intArrayOf(1026, 1302)),
    ALL_GENERATIONS("Complete", intArrayOf(1, 1025))
}
