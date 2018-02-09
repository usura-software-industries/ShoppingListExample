package com.urmwsk.common.model

import java.util.*

class ShoppingListElementModel(val id: String = UUID.randomUUID().toString(), var shoppingListId: String = "", var isPurchased: Boolean = false, val title: String = "", val addedDate: Long = Date().time) {
}