package com.urmwsk.common.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(DBShoppingList::class), (DBShoppingItem::class)], version = 1, exportSchema = false)
abstract class ShoppingListDatabase: RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}