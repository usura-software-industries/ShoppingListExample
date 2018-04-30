package com.urmwsk.feature.shoppinglist.current

import com.mikepenz.fastadapter.IItem
import com.urmwsk.core.mvp.MvpPresenter
import com.urmwsk.core.mvp.MvpView

object ShoppingListContract {

    interface View : MvpView {
        fun setList(items: List<IItem<*, *>>)

        fun showAddListDialog()

        fun showDetails(id: String)
    }


    interface Presenter : MvpPresenter<View> {
        fun addNewShoppingList(title: String)
    }

}