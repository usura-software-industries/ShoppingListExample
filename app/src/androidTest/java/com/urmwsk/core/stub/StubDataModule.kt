package com.urmwsk.core.stub

import com.urmwsk.common.db.ShoppingListProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [StubAppModule::class])
class StubDataModule {

    @Provides
    @Singleton
    fun providesShoppingListProvider(): ShoppingListProvider = StubShoppingListProvider()
}
