package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingItem
import com.urmwsk.common.model.ShoppingListElementModel

class ShoppingElementDBPopulator : Populator<ShoppingListElementModel, DBShoppingItem> {
    override fun populate(s: ShoppingListElementModel): DBShoppingItem {
        return DBShoppingItem(s.id, s.shoppingListId, s.isPurchased, s.title, s.addedDate)
    }

}