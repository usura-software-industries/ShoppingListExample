package com.urmwsk.feature.shoppinglist.current.views

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.urmwsk.common.model.ShoppingListModel
import com.urmwsk.core.R
import kotlinx.android.synthetic.main.item_shopping_list.view.*

class ShoppingListItem(val shoppingList: ShoppingListModel, private val clickCallback: (item: ShoppingListItem) -> Unit, private val deleteCallback: (item: ShoppingListItem) -> Unit) : AbstractItem<ShoppingListItem, ShoppingListItem.VH>() {

    override fun getViewHolder(v: View) = VH(v)
    override fun getType() = R.id.shoppingListItemLayout
    override fun getLayoutRes() = R.layout.item_shopping_list

    class VH(itemView: View) : FastAdapter.ViewHolder<ShoppingListItem>(itemView) {

        private val layout = itemView.shoppingListItemLayout
        private val title = itemView.shoppingListTitle
        private val deleteBtn = itemView.deleteShoppingList

        override fun bindView(item: ShoppingListItem, payloads: List<Any>) {
            title.text = item.shoppingList.title
            layout.setOnClickListener({ item.clickCallback.invoke(item) })
            deleteBtn.setOnClickListener({ item.deleteCallback.invoke(item) })
        }

        override fun unbindView(item: ShoppingListItem) {
            layout.setOnClickListener(null)
            deleteBtn.setOnClickListener(null)
        }
    }
}