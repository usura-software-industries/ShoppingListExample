package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingList
import com.urmwsk.common.model.ShoppingListModel

class ShoppingListModelPopulator : Populator<DBShoppingList, ShoppingListModel> {

    override fun populate(s: DBShoppingList): ShoppingListModel {
        return ShoppingListModel(s.id, s.title, s.addedDate)
    }

}