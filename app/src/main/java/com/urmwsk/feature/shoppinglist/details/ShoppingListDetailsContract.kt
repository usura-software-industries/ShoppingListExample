package com.urmwsk.feature.shoppinglist.details

import com.mikepenz.fastadapter.IItem
import com.urmwsk.core.mvp.MvpPresenter
import com.urmwsk.core.mvp.MvpView

object ShoppingListDetailsContract {

    interface View : MvpView {
        fun setTitle(title: String)

        fun setList(items: List<IItem<*, *>>)

        fun showAddElementDialog()

        fun closeScreen()
    }

    interface Presenter : MvpPresenter<View> {
        fun setData(shoppingListId: String)

        fun addShoppingElement(title: String)
    }

}