package com.example.mypokedexcompose.data.dataSource.mappers

import com.example.mypokedexcompose.domaindatalayer.berries.Berry
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryResult
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryEntity

class BerryMapper {
    fun toDomain(berryEntity: BerryEntity): Berry {
        return Berry(
            firmness = berryEntity.firmness,
            flavors = berryEntity.flavors,
            id = berryEntity.id,
            name = berryEntity.name,
            size = berryEntity.size,
            smoothness = berryEntity.smoothness
        )
    }

    fun toEntity(berry: Berry): BerryEntity {
        return BerryEntity(
            firmness = berry.firmness,
            flavors = berry.flavors,
            id = berry.id,
            name = berry.name,
            size = berry.size,
            smoothness = berry.smoothness,
            favorite = berry.favorite
        )
    }

    fun fromRemoteToEntity(berryResult: BerryResult): BerryEntity {
        return BerryEntity(
            firmness = berryResult.firmness,
            flavors = berryResult.flavors,
            id = berryResult.id,
            name = berryResult.name,
            size = berryResult.size,
            smoothness = berryResult.smoothness,
            favorite = false
        )
    }

    fun fromRemoteToEntityList(berries: List<BerryResult>): List<BerryEntity> {
        return berries.mapIndexed { index, berryResult ->
            BerryEntity(
                firmness = berryResult.firmness,
                flavors = berryResult.flavors,
                id = index + 1,
                name = berryResult.name,
                size = berryResult.size,
                smoothness = berryResult.smoothness,
                favorite = false
            )
        }
    }

    fun toEntityList(berries: List<Berry>): List<BerryEntity> {
        return berries.map { toEntity(it) }
    }

    fun toDomainList(berries: List<BerryEntity>): List<Berry> {
        return berries.map { toDomain(it) }
    }
}