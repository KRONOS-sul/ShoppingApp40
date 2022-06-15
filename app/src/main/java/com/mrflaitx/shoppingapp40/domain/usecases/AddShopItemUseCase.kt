package com.mrflaitx.shoppingapp40.domain.usecases

import com.mrflaitx.shoppingapp40.domain.entity.ShopItem
import com.mrflaitx.shoppingapp40.domain.repository.ShopListRepository

class AddShopItemUseCase(private val repository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}