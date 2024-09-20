package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.data.items.Item
import com.example.mypokedexcompose.data.items.ItemResult

class ItemRemoteDataSource {

    suspend fun fetchItems(): List<Item> = ItemClient.instance
        .fetchItems(304)
        .results
        .map {
            it.todomainModel()
        }

    suspend fun fetchItemById(id: Int): Item = ItemClient.instance
        .getItemById(id)

}

private fun ItemResult.todomainModel(): Item =
    Item(
        attributes = attributes,
        babyTriggerFor = babyTriggerFor,
        category = category,
        cost = cost,
        effectEntries = effectEntries,
        flavorTextEntries = flavorTextEntries,
        flingEffect = flingEffect,
        flingPower = flingPower,
        gameIndices = gameIndices,
        heldByPokemon = heldByPokemon,
        id = id,
        machines = machines,
        name = name,
        sprites = sprites
    )