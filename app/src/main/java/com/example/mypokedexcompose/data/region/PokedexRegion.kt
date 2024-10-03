package com.example.mypokedexcompose.data.region

enum class PokedexRegion(val displayName: String, val range: IntArray) {
    GENERATION_1("Kanto", intArrayOf(0, 151)),
    GENERATION_2("Johto", intArrayOf(151, 251)),
    GENERATION_3("Hoenn", intArrayOf(251, 386)),
    GENERATION_4("Sinnoh", intArrayOf(386, 494)),
    GENERATION_5("Unova", intArrayOf(494, 649)),
    GENERATION_6("Kalos", intArrayOf(649, 721)),
    GENERATION_7("Alola", intArrayOf(721, 809)),
    GENERATION_8("Galar", intArrayOf(809, 905)),
    GENERATION_9("Paldea", intArrayOf(905, 1025)),
    ESPECIAL_FORMS("Forms", intArrayOf(1025, 1302)),
    ALL_GENERATIONS("Complete", intArrayOf(0, 1025))
}
