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
    private var canEdit = true
    var items = ArrayList<ShoppingListElementItem>()
    var deleteElementClick: (item: ShoppingListElementItem) -> Unit = { deleteElement(it) }

    override fun setData(shoppingListId: String, canEdit: Boolean) {
        this.shoppingListId = shoppingListId
        this.canEdit = canEdit

        disposable.add(provider.getShoppingElement(shoppingListId).subscribe({
            it.map {
                items.add(ShoppingListElementItem(it, canEdit, deleteElementClick))
            }
            view?.setList(items)
        }, { Timber.d(it) }))

        disposable.add(provider.getListById(shoppingListId).subscribe({
            shoppingList = it
            view?.setTitle(it.title)
        }, { Timber.d(it) }))

        view?.setAddVisible(canEdit)
        view?.setItemsClickable(canEdit)
    }

    override fun addShoppingElement(title: String) {
        val newShoppingElement = ShoppingListElementModel(shoppingListId = shoppingListId, title = title)
        items.add(0, ShoppingListElementItem(newShoppingElement, canEdit, deleteElementClick))
        view?.setList(items)

        provider.insertShoppingElement(newShoppingElement)
    }

    override fun itemSelected(position: Int) {
        val element = items[position].element
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
