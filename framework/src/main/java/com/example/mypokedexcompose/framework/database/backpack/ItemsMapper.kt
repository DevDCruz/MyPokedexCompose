package com.example.mypokedexcompose.framework.database.backpack

import android.util.Log
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.framework.remote.backpack.ItemResult

class ItemsMapper {
    fun fromEntityToDomain(itemEntity: ItemEntity): BackpackItem {
        return BackpackItem(
            attributes = itemEntity.attributes,
            category = itemEntity.category,
            cost = itemEntity.cost,
            id = itemEntity.id,
            name = itemEntity.name,
            sprites = itemEntity.sprites,
            favorite = itemEntity.favorite,
            detailFetched = itemEntity.detailFetched
        )
    }

    fun fromDomainToEntity(backpackItem: BackpackItem): ItemEntity {
        return ItemEntity(
            attributes = backpackItem.attributes,
            category = backpackItem.category,
            cost = backpackItem.cost,
            id = backpackItem.id,
            name = backpackItem.name,
            sprites = backpackItem.sprites,
            favorite = backpackItem.favorite,
            detailFetched = backpackItem.detailFetched
        )
    }

    fun fromRemoteToDomain(itemResult: ItemResult): BackpackItem {
        return BackpackItem(
            attributes = itemResult.attributes,
            category = itemResult.category,
            cost = itemResult.cost,
            id = itemResult.id,
            name = itemResult.name,
            sprites = itemResult.sprites
        )
    }

    fun fromRemoteListToDomainList(itemsResults: List<ItemResult>): List<BackpackItem> {
        return itemsResults.map { fromRemoteToDomain(it) }
    }

    fun fromDomainListToEntityList(backpackItems: List<BackpackItem>): List<ItemEntity> {
        return backpackItems.map { fromDomainToEntity(it) }
    }

    fun fromEntityListToDomainList(items: List<ItemEntity>): List<BackpackItem> {
        return items.map { fromEntityToDomain(it) }
    }
}

/*fun fromRemoteListtoDomainList(itemsResults: List<ItemResult>): List<BackpackItem> {
    return itemsResults.map { fromRemoteToDomain(it) }

}

fun fromRemoteToEntityList(items: List<ItemResult>): List<ItemEntity> {
    return items.mapIndexed { index, itemResult ->
        ItemEntity(
            attributes = itemResult.attributes,
            category = itemResult.category,
            cost = itemResult.cost,
            id = index + 1,
            name = itemResult.name,
            sprites = itemResult.sprites,
            favorite = false
        )
    }
}

fun fromRemoteToEntity(backpackItemResult: BackpackItem): ItemEntity {
    return ItemEntity(
        attributes = backpackItemResult.attributes,
        category = backpackItemResult.category,
        cost = backpackItemResult.cost,
        id = backpackItemResult.id,
        name = backpackItemResult.name,
        sprites = backpackItemResult.sprites
    )
}*/