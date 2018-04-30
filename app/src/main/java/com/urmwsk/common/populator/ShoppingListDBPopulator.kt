package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingList
import com.urmwsk.common.model.ShoppingListModel

class ShoppingListDBPopulator : Populator<ShoppingListModel, DBShoppingList> {

    override fun populate(s: ShoppingListModel): DBShoppingList {
        return DBShoppingList(s.id, s.title, s.addedDate)
    }

}