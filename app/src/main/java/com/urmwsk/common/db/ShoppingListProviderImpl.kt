package com.urmwsk.common.db

import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.common.populator.ShoppingElementDBPopulator
import com.urmwsk.common.populator.ShoppingElementModelPopulator
import com.urmwsk.common.populator.ShoppingListDBPopulator
import com.urmwsk.common.populator.ShoppingListModelPopulator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShoppingListProviderImpl(private val shoppingListDao: ShoppingListDao) : ShoppingListProvider {

    override fun insertShoppingList(list: ShoppingListModel) {
        shoppingListDao.insertList(ShoppingListDBPopulator().populate(list))
    }

    override fun updateShoppingList(list: ShoppingListModel) {
        shoppingListDao.updateList(ShoppingListDBPopulator().populate(list))
    }

    override fun deleteList(list: ShoppingListModel) {
        shoppingListDao.deleteList(ShoppingListDBPopulator().populate(list))
    }

    override fun archiveList(list: ShoppingListModel) {
        list.isArchived = true
        updateShoppingList(list)
        shoppingListDao.archiveElements(list.id)
    }

    override fun getListById(id: String): Single<ShoppingListModel> {
        return shoppingListDao.getListById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { ShoppingListModelPopulator().populate(it) }
    }

    override fun getActiveLists(): Single<List<ShoppingListModel>> {
        return shoppingListDao.getActiveLists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map({ it.map { ShoppingListModelPopulator().populate(it) } })
    }

    override fun getArchivedLists(): Single<List<ShoppingListModel>> {
        return shoppingListDao.getArchivedLists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map({ it.map { ShoppingListModelPopulator().populate(it) } })
    }

    override fun insertShoppingElement(element: ShoppingListElementModel) {
        shoppingListDao.insertItem(ShoppingElementDBPopulator().populate(element))
    }

    override fun deleteShoppingElement(element: ShoppingListElementModel) {
        shoppingListDao.deleteItem(ShoppingElementDBPopulator().populate(element))
    }

    override fun getShoppingElement(shoppingListId: String): Single<List<ShoppingListElementModel>> {
        return shoppingListDao.getListItems(shoppingListId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map({ it.map { ShoppingElementModelPopulator().populate(it) } })
    }

}