package com.urmwsk.feature.shoppinglist.details.view

import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.TextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.urmwsk.common.model.ShoppingListElementModel
import com.urmwsk.core.R
import kotlinx.android.synthetic.main.item_shopping_list_element.view.*

@Suppress("DEPRECATION")
class ShoppingListElementItem(val element: ShoppingListElementModel, val shouldShowButton: Boolean, private val callback: (ShoppingListElementItem) -> Unit) : AbstractItem<ShoppingListElementItem, ShoppingListElementItem.VH>() {

    override fun getViewHolder(v: View) = VH(v)
    override fun getType() = R.id.shoppingListElementLayout
    override fun getLayoutRes() = R.layout.item_shopping_list_element

    class VH(itemView: View) : FastAdapter.ViewHolder<ShoppingListElementItem>(itemView) {

        private val layout = itemView.shoppingListElementLayout
        private val title = itemView.elementTitle
        private val delete = itemView.deleteShoppingElement
        private val divider = itemView.shoppingListInnerDivider

        override fun bindView(item: ShoppingListElementItem, payloads: List<Any>) {
            delete.setOnClickListener({ item.callback.invoke(item) })
            title.setText(item.element.title, TextView.BufferType.SPANNABLE)

            val deleteBtnVisibility = if (item.shouldShowButton) View.VISIBLE else View.GONE
            divider.visibility = deleteBtnVisibility
            delete.visibility = deleteBtnVisibility

            if (!item.element.isPurchased) {
                layout.setBackgroundColor(layout.context.resources.getColor(R.color.white))
            } else {
                layout.setBackgroundColor(layout.context.resources.getColor(R.color.gray))
                val spannable = title.text as Spannable
                spannable.setSpan(StrikethroughSpan(), 0, title.text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            }
        }

        override fun unbindView(item: ShoppingListElementItem) {
            delete.setOnClickListener(null)
        }
    }
}