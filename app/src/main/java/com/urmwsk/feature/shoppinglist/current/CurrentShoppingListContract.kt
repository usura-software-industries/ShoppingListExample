package com.urmwsk.feature.shoppinglist.current

import com.mikepenz.fastadapter.IItem
import com.urmwsk.core.mvp.MvpPresenter
import com.urmwsk.core.mvp.MvpView
import com.urmwsk.feature.shoppinglist.current.views.ShoppingListItem

object CurrentShoppingListContract {

    interface View : MvpView {
        fun setList(items: List<IItem<*, *>>)

        fun showAddListDialog()

        fun showDetails(id: String)
    }


    interface Presenter : MvpPresenter<View> {
        fun addNewShoppingList(title: String)
        fun itemSelected(position: Int)
        fun archiveList(item: ShoppingListItem)
    }

}