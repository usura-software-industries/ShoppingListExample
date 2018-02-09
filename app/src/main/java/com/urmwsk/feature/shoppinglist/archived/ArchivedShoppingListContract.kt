package com.urmwsk.feature.shoppinglist.archived

import com.mikepenz.fastadapter.IItem
import com.urmwsk.core.mvp.MvpPresenter
import com.urmwsk.core.mvp.MvpView

object ArchivedShoppingListContract {

    interface View : MvpView {
        fun setList(items: List<IItem<*, *>>)

        fun showDetails(id: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun itemSelected(position: Int)
    }

}