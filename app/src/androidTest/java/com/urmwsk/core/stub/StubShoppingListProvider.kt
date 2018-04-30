package com.urmwsk.core.stub

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import io.reactivex.Single

class StubShoppingListProvider : ShoppingListProvider {

    companion object {
        val testItem1 = ShoppingListModel("testId1", false, "testTitle1", 1)
        val testItem2 = ShoppingListModel("testId2", true, "testTitle2", 5)

        val testElement1 = ShoppingListElementModel("elementId1", testItem1.id, false, "element1", 0)
        val testElement2 = ShoppingListElementModel("elementId2", testItem1.id, true, "element2", 5)
    }

    override fun insertShoppingList(list: ShoppingListModel) {
    }

    override fun updateShoppingList(list: ShoppingListModel) {
    }

    override fun deleteList(list: ShoppingListModel) {
    }

    override fun getListById(id: String): Single<ShoppingListModel> {
        return if (id == testItem1.id) {
            Single.just(testItem1)
        } else {
            Single.just(testItem2)
        }
    }

    override fun getShoppingLists(): Single<List<ShoppingListModel>> {
        return Single.just(arrayListOf(testItem1))
    }

    override fun getArchivedLists(): Single<List<ShoppingListModel>> {
        return Single.just(arrayListOf(testItem2))
    }

    override fun archiveList(list: ShoppingListModel) {
    }

    override fun insertShoppingElement(element: ShoppingListElementModel) {
    }

    override fun deleteShoppingElement(element: ShoppingListElementModel) {
    }

    override fun getShoppingElement(shoppingListId: String): Single<List<ShoppingListElementModel>> {
        return Single.just(arrayListOf(testElement1, testElement2))
    }

}