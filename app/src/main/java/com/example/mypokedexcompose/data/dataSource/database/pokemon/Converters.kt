package com.example.mypokedexcompose.data.dataSource.database.pokemon

import androidx.room.TypeConverter
import com.example.mypokedexcompose.data.berries.Firmness
import com.example.mypokedexcompose.data.berries.Flavor
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

    @TypeConverter
    fun fromFirmness(firmness: Firmness?): String? {
        return Gson().toJson(firmness)
    }

    @TypeConverter
    fun toFirmness(firmnessString: String?): Firmness? {
        return if (firmnessString == null) {
            null
        } else {
            Gson().fromJson(firmnessString, Firmness::class.java)
        }
    }

    @TypeConverter
    fun fromFlavorsList(flavors: List<Flavor>?): String? {
        return Gson().toJson(flavors)
    }

    @TypeConverter
    fun toFlavorsList(flavorsString: String?): List<Flavor>? {
        return if (flavorsString == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<Flavor>>() {}.type
            Gson().fromJson(flavorsString, listType)
        }
    }
}