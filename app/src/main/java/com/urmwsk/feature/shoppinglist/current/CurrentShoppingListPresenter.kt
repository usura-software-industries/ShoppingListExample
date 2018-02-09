package com.urmwsk.feature.shoppinglist.current

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.core.mvp.MvpPresenterImpl
import com.urmwsk.feature.shoppinglist.current.views.ShoppingListItem
import timber.log.Timber
import java.util.*

class CurrentShoppingListPresenter(private val provider: ShoppingListProvider) : MvpPresenterImpl<CurrentShoppingListContract.View>(), CurrentShoppingListContract.Presenter {

    var items = ArrayList<ShoppingListItem>()
    val archiveItemClick: (item: ShoppingListItem) -> Unit = { archiveList(it) }

    override fun bindView(view: CurrentShoppingListContract.View) {
        super.bindView(view)

        disposable.add(provider.getActiveLists().subscribe({
            it.map {
                items.add(ShoppingListItem(it, archiveItemClick))
            }
            view.setList(items)
        }, { Timber.d(it.toString()) }))
    }

    override fun addNewShoppingList(title: String) {
        val newShoppingList = ShoppingListModel(title = title)
        items.add(0, ShoppingListItem(newShoppingList, archiveItemClick))
        view?.setList(items)

        provider.insertShoppingList(newShoppingList)
    }

    override fun itemSelected(position: Int) {
        view?.showDetails(items[position].shoppingList.id)
    }

    override fun archiveList(item: ShoppingListItem) {
        items.remove(item)
        view?.setList(items)

        provider.archiveList(item.shoppingList)
    }

}
