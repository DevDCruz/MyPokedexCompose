package com.example.mypokedexcompose.framework.mappers

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.framework.database.backpack.ItemEntity
import com.example.mypokedexcompose.framework.remote.backpack.ItemResult

class ItemsMapper {
    fun fromEntityToDomain(itemEntity: ItemEntity): BackpackItemDomain {
        return BackpackItemDomain(
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

    fun fromDomainToEntity(backpackItemDomain: BackpackItemDomain): ItemEntity {
        return ItemEntity(
            attributes = backpackItemDomain.attributes,
            category = backpackItemDomain.category,
            cost = backpackItemDomain.cost,
            id = backpackItemDomain.id,
            name = backpackItemDomain.name,
            sprites = backpackItemDomain.sprites,
            favorite = backpackItemDomain.favorite,
            detailFetched = backpackItemDomain.detailFetched
        )
    }

    fun fromRemoteToDomain(itemResult: ItemResult): BackpackItemDomain {
        return BackpackItemDomain(
            attributes = itemResult.attributes,
            category = itemResult.category,
            cost = itemResult.cost,
            id = itemResult.id,
            name = itemResult.name,
            sprites = itemResult.sprites
        )
    }

    fun fromDomainListToEntityList(backpackItemDomains: List<BackpackItemDomain>): List<ItemEntity> {
        return backpackItemDomains.map { fromDomainToEntity(it) }
    }

    fun fromEntityListToDomainList(items: List<ItemEntity>): List<BackpackItemDomain> {
        return items.map { fromEntityToDomain(it) }
    }
}

