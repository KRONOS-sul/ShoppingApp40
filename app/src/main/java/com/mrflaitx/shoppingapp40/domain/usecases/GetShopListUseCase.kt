package com.mrflaitx.shoppingapp40.domain.usecases

import com.mrflaitx.shoppingapp40.domain.entity.ShopItem
import com.mrflaitx.shoppingapp40.domain.repository.ShopListRepository

class GetShopListUseCase(private val repository: ShopListRepository) {

    fun getShopList() = repository.getShopList()
}