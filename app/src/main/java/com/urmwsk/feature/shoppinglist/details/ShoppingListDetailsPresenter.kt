package com.urmwsk.feature.shoppinglist.details

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.core.mvp.MvpPresenterImpl
import com.urmwsk.feature.shoppinglist.details.view.ShoppingListElementItem
import timber.log.Timber

class ShoppingListDetailsPresenter(private val provider: ShoppingListProvider) : MvpPresenterImpl<ShoppingListDetailsContract.View>(), ShoppingListDetailsContract.Presenter {

    private var shoppingList: ShoppingListModel? = null
    private var shoppingListId = ""
    var items = ArrayList<ShoppingListElementItem>()
    var elementClick: (item: ShoppingListElementItem) -> Unit = {
        elementSelected(it.element)
    }
    var deleteElementClick: (item: ShoppingListElementItem) -> Unit = { deleteElement(it) }

    override fun setData(shoppingListId: String) {
        this.shoppingListId = shoppingListId

        disposable.add(provider.getShoppingElement(shoppingListId).subscribe({
            it.map {
                items.add(ShoppingListElementItem(it, elementClick, deleteElementClick))
            }
            view?.setList(items)
        }, { Timber.d(it) }))

        disposable.add(provider.getListById(shoppingListId).subscribe({
            shoppingList = it
            view?.setTitle(it.title)
        }, { Timber.d(it) }))
    }

    override fun addShoppingElement(title: String) {
        val newShoppingElement = ShoppingListElementModel(shoppingListId = shoppingListId, title = title)
        items.add(0, ShoppingListElementItem(newShoppingElement, elementClick, deleteElementClick))
        view?.setList(items)

        provider.insertShoppingElement(newShoppingElement)
    }

    private fun elementSelected(element: ShoppingListElementModel) {
        element.isPurchased = !element.isPurchased
        val sortedList = items.sortedWith(compareBy<ShoppingListElementItem> { it.element.isPurchased }.thenByDescending { it.element.addedDate })
        items = ArrayList(sortedList)
        view?.setList(items)

        provider.insertShoppingElement(element)
    }

    private fun deleteElement(item: ShoppingListElementItem) {
        items.remove(item)
        view?.setList(items)

        provider.deleteShoppingElement(item.element)
    }
}
