package com.urmwsk.feature.shoppinglist.archived

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.core.mvp.MvpPresenterImpl
import com.urmwsk.feature.shoppinglist.archived.view.ArchivedShoppingListItem
import timber.log.Timber
import java.util.*

class ArchivedShoppingListPresenter(private val provider: ShoppingListProvider) : MvpPresenterImpl<ArchivedShoppingListContract.View>(), ArchivedShoppingListContract.Presenter {

    var items = ArrayList<ArchivedShoppingListItem>()

    override fun bindView(view: ArchivedShoppingListContract.View) {
        super.bindView(view)

        disposable.add(provider.getArchivedLists().subscribe({
            it.map {
                items.add(ArchivedShoppingListItem(it))
            }
            view.setList(items)
        }, { Timber.d(it.toString()) }))
    }

    override fun itemSelected(position: Int) {
        view?.showDetails(items[position].shoppingList.id)
    }

}
