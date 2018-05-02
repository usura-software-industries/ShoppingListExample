package com.urmwsk.common.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DBShoppingElement(@PrimaryKey var id: String = "", var isPurchased: Boolean = false, var title: String = "", var addedDate: Long = 0) : RealmObject()