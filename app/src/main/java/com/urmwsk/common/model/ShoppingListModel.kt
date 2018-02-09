package com.urmwsk.common.model

import java.util.*

class ShoppingListModel(val id: String = UUID.randomUUID().toString(), var isArchived: Boolean = false, val title: String = "", val addedDate: Long = Date().time) {
}
