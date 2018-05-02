package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingElement
import com.urmwsk.common.model.ShoppingListElementModel

class ShoppingElementDBPopulator : Populator<ShoppingListElementModel, DBShoppingElement> {
    override fun populate(s: ShoppingListElementModel): DBShoppingElement {
        return DBShoppingElement(s.id, s.isPurchased, s.title, s.addedDate)
    }

}