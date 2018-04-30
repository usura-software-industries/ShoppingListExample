package com.urmwsk.common.injection.modules

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.injection.annotation.PerFragment
import com.urmwsk.feature.shoppinglist.current.ShoppingListContract
import com.urmwsk.feature.shoppinglist.current.ShoppingListPresenter
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsContract
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    @PerFragment
    fun currentShoppingListPresenter(provider: ShoppingListProvider): ShoppingListContract.Presenter {
        return ShoppingListPresenter(provider)
    }

    @Provides
    @PerFragment
    fun detailsListPresenter(provider: ShoppingListProvider): ShoppingListDetailsContract.Presenter {
        return ShoppingListDetailsPresenter(provider)
    }
}
