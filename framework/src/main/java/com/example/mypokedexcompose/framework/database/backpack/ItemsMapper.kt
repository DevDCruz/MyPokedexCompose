package com.example.mypokedexcompose.framework.database.backpack

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

    fun fromDomainListToEntityList(backpackItems: List<BackpackItem>): List<ItemEntity> {
        return backpackItems.map { fromDomainToEntity(it) }
    }

    fun fromEntityListToDomainList(items: List<ItemEntity>): List<BackpackItem> {
        return items.map { fromEntityToDomain(it) }
    }
}

