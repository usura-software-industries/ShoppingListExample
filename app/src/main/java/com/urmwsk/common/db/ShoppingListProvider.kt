package com.urmwsk.common.db

import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import io.reactivex.Single

interface ShoppingListProvider {

    fun insertShoppingList(list: ShoppingListModel)

    fun updateShoppingList(list: ShoppingListModel)

    fun deleteList(list: ShoppingListModel)

    fun getListById(id: String): Single<ShoppingListModel>

    fun getActiveLists(): Single<List<ShoppingListModel>>

    fun getArchivedLists(): Single<List<ShoppingListModel>>

    fun archiveList(list: ShoppingListModel)

    fun insertShoppingElement(element: ShoppingListElementModel)

    fun deleteShoppingElement(element: ShoppingListElementModel)

    fun getShoppingElement(shoppingListId: String): Single<List<ShoppingListElementModel>>
}