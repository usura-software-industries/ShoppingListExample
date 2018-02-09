package com.urmwsk.feature.shoppinglist.details

import com.mikepenz.fastadapter.IItem
import com.urmwsk.core.mvp.MvpPresenter
import com.urmwsk.core.mvp.MvpView

object ShoppingListDetailsContract {

    interface View : MvpView {
        fun setTitle(title: String)

        fun setList(items: List<IItem<*, *>>)

        fun showAddElementDialog()

        fun setAddVisible(visible: Boolean)

        fun setItemsClickable(clickable: Boolean)

        fun closeScreen()
    }

    interface Presenter : MvpPresenter<View> {
        fun setData(shoppingListId: String, canEdit: Boolean)
        fun addShoppingElement(title: String)
        fun itemSelected(position: Int)
    }

}