package com.urmwsk.common.injection.modules

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.db.ShoppingListProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataModule {

    @Provides
    @Singleton
    fun providesShoppingListProvider(): ShoppingListProvider = ShoppingListProviderImpl()
}
