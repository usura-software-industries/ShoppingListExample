package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingElement
import com.urmwsk.common.model.ShoppingListElementModel

class ShoppingElementModelPopulator : Populator<DBShoppingElement, ShoppingListElementModel> {
    override fun populate(s: DBShoppingElement): ShoppingListElementModel {
        return ShoppingListElementModel(s.id, s.isPurchased, s.title, s.addedDate)
    }

}