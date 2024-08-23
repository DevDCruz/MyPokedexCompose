package com.example.mypokedexcompose.data.items

class ItemRepository {

    suspend fun fetchItems(): List<Item> = ItemClient
        .instance
        .fetchItems(64)
        .results
        .map {
            it.todomainModel()
        }


    suspend fun fetchItemById(id: Int): Item = ItemClient
        .instance
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