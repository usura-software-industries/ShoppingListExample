package com.urmwsk.common.db

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DBShoppingList(@PrimaryKey var id: String = "", var title: String = "", var addedDate: Long = 0, var elements: RealmList<DBShoppingElement> = RealmList()) : RealmObject()