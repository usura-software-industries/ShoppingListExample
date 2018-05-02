package com.urmwsk.common.populator

import com.urmwsk.common.db.DBShoppingElement
import com.urmwsk.common.db.DBShoppingList
import com.urmwsk.common.model.ShoppingListModel
import io.realm.RealmList

class ShoppingListDBPopulator : Populator<ShoppingListModel, DBShoppingList> {

    override fun populate(s: ShoppingListModel): DBShoppingList {
        val dbList: RealmList<DBShoppingElement> = RealmList()
        s.elements.map { dbList.add(ShoppingElementDBPopulator().populate(it)) }
        return DBShoppingList(s.id, s.title, s.addedDate, dbList)
    }

}