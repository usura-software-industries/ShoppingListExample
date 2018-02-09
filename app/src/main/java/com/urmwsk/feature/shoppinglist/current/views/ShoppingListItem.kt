package com.urmwsk.feature.shoppinglist.current.views

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.core.R
import kotlinx.android.synthetic.main.item_shopping_list.view.*

class ShoppingListItem(val shoppingList: ShoppingListModel, private val callback: (item: ShoppingListItem) -> Unit) : AbstractItem<ShoppingListItem, ShoppingListItem.VH>() {

    override fun getViewHolder(v: View) = VH(v)
    override fun getType() = R.id.shoppingListItemLayout
    override fun getLayoutRes() = R.layout.item_shopping_list

    class VH(itemView: View) : FastAdapter.ViewHolder<ShoppingListItem>(itemView) {

        private val title = itemView.shoppingListTitle
        private val archiveList = itemView.shoppingListArchive

        override fun bindView(item: ShoppingListItem, payloads: List<Any>) {
            title.text = item.shoppingList.title
            archiveList.setOnClickListener({ item.callback.invoke(item) })
        }

        override fun unbindView(item: ShoppingListItem) {
            archiveList.setOnClickListener(null)
        }
    }
}