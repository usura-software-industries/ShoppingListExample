package com.urmwsk.common.model

import java.util.*

class ShoppingListModel(val id: String = UUID.randomUUID().toString(), val title: String = "", val addedDate: Long = Date().time) {
}
