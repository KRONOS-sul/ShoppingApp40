package com.mrflaitx.shoppingapp40.domain.repository

import androidx.lifecycle.LiveData
import com.mrflaitx.shoppingapp40.domain.entity.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    // refactor
    fun getShopList(): LiveData<List<ShopItem>>


}