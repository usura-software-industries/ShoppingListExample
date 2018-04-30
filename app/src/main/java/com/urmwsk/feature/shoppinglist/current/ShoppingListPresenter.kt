package com.urmwsk.feature.shoppinglist.current

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.core.mvp.MvpPresenterImpl
import com.urmwsk.feature.shoppinglist.current.views.ShoppingListItem
import timber.log.Timber

class ShoppingListPresenter(private val provider: ShoppingListProvider) : MvpPresenterImpl<ShoppingListContract.View>(), ShoppingListContract.Presenter {

    var items = ArrayList<ShoppingListItem>()
    val itemClick: (item: ShoppingListItem) -> Unit = { view?.showDetails(it.shoppingList.id) }
    val deleteClick: (item: ShoppingListItem) -> Unit = {
        provider.deleteList(it.shoppingList)
        items.remove(it)
        view?.setList(items)
    }

    override fun bindView(view: ShoppingListContract.View) {
        super.bindView(view)

        disposable.add(provider.getShoppingLists().subscribe({
            items = ArrayList(it.map { ShoppingListItem(it, clickCallback = itemClick, deleteCallback = deleteClick)})
            view.setList(items)
        }, { Timber.d(it.toString()) }))
    }

    override fun addNewShoppingList(title: String) {
        val newShoppingList = ShoppingListModel(title = title)
        items.add(0, ShoppingListItem(newShoppingList, clickCallback = itemClick, deleteCallback = deleteClick))
        view?.setList(items)

        provider.insertShoppingList(newShoppingList)
    }
}
