package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingItem
import com.urmwsk.common.model.ShoppingListElementModel

class ShoppingElementModelPopulator : Populator<DBShoppingItem, ShoppingListElementModel> {
    override fun populate(s: DBShoppingItem): ShoppingListElementModel {
        return ShoppingListElementModel(s.id, s.shoppingListId, s.isPurchased, s.title, s.addedDate)
    }

}