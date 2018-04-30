package com.urmwsk.common.db

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Single

@Dao
interface ShoppingListDao {

    @Insert(onConflict = REPLACE)
    fun insertList(list: DBShoppingList)

    @Update()
    fun updateList(list: DBShoppingList)

    @Delete
    fun deleteList(list: DBShoppingList)

    @Query("SELECT * FROM DBShoppingList " +
            "WHERE id = :id")
    fun getListById(id: String): Single<DBShoppingList>

    @Query("SELECT * FROM DBShoppingList " +
            "ORDER BY addedDate DESC")
    fun getLists(): Single<List<DBShoppingList>>
    @Insert(onConflict = REPLACE)
    fun insertItem(list: DBShoppingItem)

    @Delete
    fun deleteItem(list: DBShoppingItem)

    @Query("UPDATE DBShoppingItem " +
            "SET isPurchased = 1 " +
            "WHERE shoppingListId = :shoppingListId")
    fun archiveElements(shoppingListId: String)

    @Query("SELECT * FROM DBShoppingItem " +
            "WHERE shoppingListId = :shoppingListId " +
            "ORDER BY isPurchased ASC, addedDate DESC")
    fun getListItems(shoppingListId: String): Single<List<DBShoppingItem>>
}