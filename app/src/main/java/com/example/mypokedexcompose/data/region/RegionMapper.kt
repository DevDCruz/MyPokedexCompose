package com.example.mypokedexcompose.data.region

import com.example.mypokedexcompose.data.pokedex.PokedexRegion

class RegionMapper {
    fun mapLocationtoPokedexRegion(location: String): PokedexRegion {
        return when (location) {
            "CN" -> PokedexRegion.GENERATION_1
            "JP" -> PokedexRegion.GENERATION_2
            "KR" -> PokedexRegion.GENERATION_3
            "IN" -> PokedexRegion.GENERATION_4
            "US" -> PokedexRegion.GENERATION_5
            "FR" -> PokedexRegion.GENERATION_6
            "AU" -> PokedexRegion.GENERATION_7
            "UK" -> PokedexRegion.GENERATION_8
            "ES" -> PokedexRegion.GENERATION_9
            else -> PokedexRegion.ALL_GENERATIONS
        }
    }
}