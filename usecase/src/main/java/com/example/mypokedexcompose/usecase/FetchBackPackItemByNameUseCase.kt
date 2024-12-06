package com.example.mypokedexcompose.usecase


import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBackPackItemByNameUseCase @Inject constructor(
    private val backPackItemRepository: IBackPackItemRepository
) {
    suspend operator fun invoke(name: String): Flow<BackpackItemDomain?> =
        backPackItemRepository.fetchBackPackItemByName(name)
}