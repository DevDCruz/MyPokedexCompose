package com.example.mypokedexcompose.framework.remote.backpack

data class ItemsListResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ItemResult>
) {
    override fun toString(): String {
        return "ItemsListResult(count=$count, next=$next, previous=$previous, results=$results)"
    }
}