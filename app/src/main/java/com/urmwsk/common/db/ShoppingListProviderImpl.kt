package com.urmwsk.common.db

import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.common.populator.ShoppingElementDBPopulator
import com.urmwsk.common.populator.ShoppingListDBPopulator
import com.urmwsk.common.populator.ShoppingListModelPopulator
import io.reactivex.Flowable
import io.realm.Realm

class ShoppingListProviderImpl : ShoppingListProvider {

    override fun insertShoppingList(list: ShoppingListModel) {
        Realm.getDefaultInstance().executeTransactionAsync({
            it.copyToRealmOrUpdate(ShoppingListDBPopulator().populate(list))
        })
    }

    override fun deleteList(list: ShoppingListModel) {
        Realm.getDefaultInstance().executeTransactionAsync({
            val toDelete = it.where(DBShoppingList::class.java)
                    .equalTo("id", list.id)
                    .findAll()
            toDelete.deleteAllFromRealm()
        })
    }

    override fun getListById(id: String): Flowable<ShoppingListModel> {
        return Realm.getDefaultInstance()
                .where(DBShoppingList::class.java)
                .equalTo("id", id)
                .findAllAsync()
                .asFlowable()
                .map { Realm.getDefaultInstance().copyFromRealm(it) }
                .map { it[0] }
                .map { ShoppingListModelPopulator().populate(it) }
                .map {
                    it.elements.sortedWith(compareBy<ShoppingListElementModel> { it.isPurchased }.thenByDescending { it.addedDate })
                    return@map it
                }
    }

    override fun getShoppingLists(): Flowable<List<ShoppingListModel>> {
        return Realm.getDefaultInstance()
                .where(DBShoppingList::class.java)
                .sort("addedDate")
                .findAllAsync()
                .asFlowable()
                .map { Realm.getDefaultInstance().copyFromRealm(it) }
                .map({ it.map { ShoppingListModelPopulator().populate(it) } })
    }

    override fun insertShoppingElement(list: ShoppingListModel, element: ShoppingListElementModel) {
        Realm.getDefaultInstance().executeTransactionAsync({
            val existingList = it.where(DBShoppingList::class.java)
                    .equalTo("id", list.id)
                    .findFirst()
            existingList!!.elements.add(ShoppingElementDBPopulator().populate(element))
//            it.copyToRealmOrUpdate(existingList)
        })
    }

    override fun deleteShoppingElement(element: ShoppingListElementModel) {
        Realm.getDefaultInstance().executeTransactionAsync({
            val toDelete = it.where(DBShoppingElement::class.java)
                    .equalTo("id", element.id)
                    .findAll()
            toDelete.deleteAllFromRealm()
        })
    }

}