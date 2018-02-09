package com.urmwsk.common.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(indices = [(Index(value = ["shoppingListId"], name = "shoppingId"))], foreignKeys = [ForeignKey(entity = DBShoppingList::class, parentColumns = ["id"], childColumns = ["shoppingListId"], onDelete = CASCADE)])
class DBShoppingItem(@PrimaryKey var id: String, var shoppingListId: String, var isPurchased: Boolean, var title: String, var addedDate: Long)