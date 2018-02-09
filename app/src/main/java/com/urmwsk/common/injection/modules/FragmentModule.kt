package com.urmwsk.common.injection.modules

import com.urmwsk.common.db.ShoppingListProvider
import com.urmwsk.common.injection.annotation.PerFragment
import com.urmwsk.feature.shoppinglist.archived.ArchivedShoppingListContract
import com.urmwsk.feature.shoppinglist.archived.ArchivedShoppingListPresenter
import com.urmwsk.feature.shoppinglist.current.CurrentShoppingListContract
import com.urmwsk.feature.shoppinglist.current.CurrentShoppingListPresenter
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsContract
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    @PerFragment
    fun currentShoppingListPresenter(provider: ShoppingListProvider): CurrentShoppingListContract.Presenter {
        return CurrentShoppingListPresenter(provider)
    }

    @Provides
    @PerFragment
    fun archivedListPresenter(provider: ShoppingListProvider): ArchivedShoppingListContract.Presenter {
        return ArchivedShoppingListPresenter(provider)
    }

    @Provides
    @PerFragment
    fun detailsListPresenter(provider: ShoppingListProvider): ShoppingListDetailsContract.Presenter {
        return ShoppingListDetailsPresenter(provider)
    }
}
