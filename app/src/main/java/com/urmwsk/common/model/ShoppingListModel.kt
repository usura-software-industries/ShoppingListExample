package com.urmwsk.common.model

import java.util.*
import kotlin.collections.ArrayList

class ShoppingListModel(val id: String = UUID.randomUUID().toString(), val title: String = "", val addedDate: Long = Date().time, val elements: List<ShoppingListElementModel> = ArrayList())
