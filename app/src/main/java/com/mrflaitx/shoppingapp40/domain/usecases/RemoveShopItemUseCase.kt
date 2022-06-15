package com.mrflaitx.shoppingapp40.domain.usecases

import com.mrflaitx.shoppingapp40.domain.entity.ShopItem
import com.mrflaitx.shoppingapp40.domain.repository.ShopListRepository

class RemoveShopItemUseCase(private val repository: ShopListRepository) {
    fun removeShopItem(shopItem: ShopItem){
        repository.removeShopItem(shopItem)
    }
}