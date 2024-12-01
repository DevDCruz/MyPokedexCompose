package com.example.mypokedexcompose.framework.mappers

import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.framework.database.berries.BerryEntity
import com.example.mypokedexcompose.framework.remote.berries.BerryResult

class BerryMapper {
    fun fromEntityToDomain(berryEntity: BerryEntity): BerryDomain {
        return BerryDomain(
            firmness = berryEntity.firmness,
            flavors = berryEntity.flavors,
            id = berryEntity.id,
            name = berryEntity.name,
            size = berryEntity.size,
            smoothness = berryEntity.smoothness,
            favorite = berryEntity.favorite,
            detailFetched = berryEntity.detailFetched
        )
    }

    fun fromDomainToEntity(berryDomain: BerryDomain): BerryEntity {
        return BerryEntity(
            firmness = berryDomain.firmness,
            flavors = berryDomain.flavors,
            id = berryDomain.id,
            name = berryDomain.name,
            size = berryDomain.size,
            smoothness = berryDomain.smoothness,
            favorite = berryDomain.favorite,
            detailFetched = berryDomain.detailFetched
        )
    }

    fun fromRemoteToDomain(berryResult: BerryResult): BerryDomain {
        return BerryDomain(
            firmness = berryResult.firmness,
            flavors = berryResult.flavors,
            id = berryResult.id,
            name = berryResult.name,
            size = berryResult.size,
            smoothness = berryResult.smoothness
        )
    }

    fun fromDomainListToEntityList(berries: List<BerryDomain>): List<BerryEntity> {
        return berries.map { fromDomainToEntity(it) }
    }

    fun fromEntityListToDomainList(berries: List<BerryEntity>): List<BerryDomain> {
        return berries.map { fromEntityToDomain(it) }
    }
}