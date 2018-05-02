package com.urmwsk.common.db

import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import io.reactivex.Flowable

interface ShoppingListProvider {

    fun insertShoppingList(list: ShoppingListModel)

    fun deleteList(list: ShoppingListModel)

    fun getListById(id: String): Flowable<ShoppingListModel>

    fun getShoppingLists(): Flowable<List<ShoppingListModel>>

    fun insertShoppingElement(list: ShoppingListModel, element: ShoppingListElementModel)

    fun deleteShoppingElement(element: ShoppingListElementModel)
}