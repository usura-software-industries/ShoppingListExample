package com.urmwsk.common.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class DBShoppingList(@PrimaryKey var id: String, var isArchived: Boolean, var title: String, var addedDate: Long)