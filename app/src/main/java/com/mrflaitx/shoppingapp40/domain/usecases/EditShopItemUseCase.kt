package com.mrflaitx.shoppingapp40.domain.usecases

import com.mrflaitx.shoppingapp40.domain.entity.ShopItem
import com.mrflaitx.shoppingapp40.domain.repository.ShopListRepository

class EditShopItemUseCase(private val repository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}