package com.example.mypokedexcompose.data.dataSource.mappers

import com.example.mypokedexcompose.data.dataSource.local.database.backpack.ItemEntity
import com.example.mypokedexcompose.data.dataSource.remote.backpack.ItemResult
import com.example.mypokedexcompose.data.items.Item

class ItemsMapper {
    fun toDomain(itemEntity: ItemEntity): Item {
        return Item(
            attributes = itemEntity.attributes,
            category = itemEntity.category,
            cost = itemEntity.cost,
            id = itemEntity.id,
            name = itemEntity.name,
            sprites = itemEntity.sprites
        )
    }

    fun toEntity(item: Item): ItemEntity {
        return ItemEntity(
            attributes = item.attributes,
            category = item.category,
            cost = item.cost,
            id = item.id,
            name = item.name,
            sprites = item.sprites,
            favorite = item.favorite
        )
    }

    fun fromRemoteToEntity(itemResult: Item): ItemEntity {
        return ItemEntity(
            attributes = itemResult.attributes,
            category = itemResult.category,
            cost = itemResult.cost,
            id = itemResult.id,
            name = itemResult.name,
            sprites = itemResult.sprites
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

    fun toEntityList(items: List<Item>): List<ItemEntity> {
        return items.map { toEntity(it) }
    }

    fun toDomainList(items: List<ItemEntity>): List<Item> {
        return items.map { toDomain(it) }
    }
}