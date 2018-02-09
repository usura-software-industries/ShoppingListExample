package com.urmwsk.feature.shoppinglist.archived.view

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.core.R
import kotlinx.android.synthetic.main.item_archived_shopping_list.view.*

class ArchivedShoppingListItem(val shoppingList: ShoppingListModel) : AbstractItem<ArchivedShoppingListItem, ArchivedShoppingListItem.VH>() {

    override fun getViewHolder(v: View) = VH(v)
    override fun getType() = R.id.archivedShoppingListItemLayout
    override fun getLayoutRes() = R.layout.item_archived_shopping_list

    class VH(itemView: View) : FastAdapter.ViewHolder<ArchivedShoppingListItem>(itemView) {

        private val title = itemView.archivedShoppingListTitle

        override fun bindView(item: ArchivedShoppingListItem, payloads: List<Any>) {
            title.text = item.shoppingList.title
        }

        override fun unbindView(item: ArchivedShoppingListItem) {
            //donth
        }
    }
}

