package com.example.mypokedexcompose.data.dataSource.database

import androidx.room.TypeConverter
import com.example.mypokedexcompose.domain.berries.Firmness
import com.example.mypokedexcompose.domain.berries.Flavor
import com.example.mypokedexcompose.domain.backpackItems.Attribute
import com.example.mypokedexcompose.domain.backpackItems.Category
import com.example.mypokedexcompose.domain.backpackItems.Sprites
import com.example.mypokedexcompose.domain.pokemon.Type
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

    @TypeConverter
    fun fromAtributesList(attributes: List<Attribute>?): String? {
        return Gson().toJson(attributes)
    }

    @TypeConverter
    fun toAttributesList(attributesString: String?): List<Attribute>? {
        return if (attributesString == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<Attribute>>() {}.type
            Gson().fromJson(attributesString, listType)
        }
    }

    @TypeConverter
    fun fromCategory(category: Category?): String? {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun toCategory(categoryString: String?): Category? {
        return if (categoryString == null) {
            null
        } else {
            Gson().fromJson(categoryString, Category::class.java)
        }
    }

    @TypeConverter
    fun fromSprites(sprites: Sprites?): String? {
        return Gson().toJson(sprites)
    }

    @TypeConverter
    fun toSprites(spritesString: String?): Sprites? {
        return if (spritesString == null) {
            null
        } else {
            Gson().fromJson(spritesString, Sprites::class.java)
        }
    }

}