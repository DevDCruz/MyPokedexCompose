package com.example.mypokedexcompose.data.dataSource.mappers

import com.example.mypokedexcompose.data.dataSource.local.backpack.ItemEntity
import com.example.mypokedexcompose.data.dataSource.remote.backpack.ItemResult
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem

class ItemsMapper {
    fun toDomain(itemEntity: ItemEntity): BackpackItem {
        return BackpackItem(
            attributes = itemEntity.attributes,
            category = itemEntity.category,
            cost = itemEntity.cost,
            id = itemEntity.id,
            name = itemEntity.name,
            sprites = itemEntity.sprites
        )
    }

    fun toEntity(backpackItem: BackpackItem): ItemEntity {
        return ItemEntity(
            attributes = backpackItem.attributes,
            category = backpackItem.category,
            cost = backpackItem.cost,
            id = backpackItem.id,
            name = backpackItem.name,
            sprites = backpackItem.sprites,
            favorite = backpackItem.favorite
        )
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

    fun toEntityList(backpackItems: List<BackpackItem>): List<ItemEntity> {
        return backpackItems.map { toEntity(it) }
    }

    fun toDomainList(items: List<ItemEntity>): List<BackpackItem> {
        return items.map { toDomain(it) }
    }
}