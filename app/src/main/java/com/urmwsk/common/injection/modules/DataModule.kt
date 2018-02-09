package com.urmwsk.common.injection.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.urmwsk.common.db.ShoppingListDao
import com.urmwsk.common.db.ShoppingListDatabase
import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.db.ShoppingListProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataModule {

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): ShoppingListDatabase = Room.databaseBuilder(context, ShoppingListDatabase::class.java, "shopping-items-db").allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun providesToDoDao(database: ShoppingListDatabase) = database.shoppingListDao()

    @Provides
    @Singleton
    fun providesShoppingListProvider(dao: ShoppingListDao): ShoppingListProvider = ShoppingListProviderImpl(dao)
}
