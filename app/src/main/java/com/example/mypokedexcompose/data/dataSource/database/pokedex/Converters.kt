package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.TypeConverter
import com.example.mypokedexcompose.data.pokemon.Type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromTypesList(types: List<Type>?): String? {
        return Gson().toJson(types)
    }

    @TypeConverter
    fun toTypesList(typesString: String?): List<Type>? {
        return if (typesString == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<Type>>() {}.type
            Gson().fromJson(typesString, listType)
        }
    }
}