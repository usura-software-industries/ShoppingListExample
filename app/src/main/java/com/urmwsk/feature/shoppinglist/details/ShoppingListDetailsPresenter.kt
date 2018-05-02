package com.urmwsk.feature.shoppinglist.details

import com.chauthai.swipereveallayout.ViewBinderHelper
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
    val swipeHelper = ViewBinderHelper()

    override fun setData(shoppingListId: String) {
        this.shoppingListId = shoppingListId

        disposable.add(provider.getListById(shoppingListId).subscribe({
            shoppingList = it
            items.clear()
            it.elements.map {
                items.add(ShoppingListElementItem(it, elementClick, deleteElementClick, swipeHelper))
            }
            view?.setTitle(it.title)
            view?.setList(items)
            disposable.clear()
        }, { Timber.d(it) }))
    }

    override fun addShoppingElement(title: String) {
        val newShoppingElement = ShoppingListElementModel(title = title)
        items.add(0, ShoppingListElementItem(newShoppingElement, elementClick, deleteElementClick, swipeHelper))
        view?.setList(items)

        provider.insertShoppingElement(shoppingList!!, newShoppingElement)
    }

    private fun elementSelected(element: ShoppingListElementModel) {
        element.isPurchased = !element.isPurchased
        val sortedList = items.sortedWith(compareBy<ShoppingListElementItem> { it.element.isPurchased }.thenByDescending { it.element.addedDate })
        items = ArrayList(sortedList)
        view?.setList(items)

        provider.insertShoppingElement(shoppingList!!, element)
    }

    private fun deleteElement(item: ShoppingListElementItem) {
        items.remove(item)
        view?.setList(items)

        provider.deleteShoppingElement(item.element)
    }
}
