package com.urmwsk.common.injection.components

import com.urmwsk.common.injection.annotation.PerFragment
import com.urmwsk.common.injection.modules.FragmentModule
import com.urmwsk.feature.shoppinglist.archived.ArchivedShoppingListFragment
import com.urmwsk.feature.shoppinglist.current.CurrentShoppingListFragment
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {

    fun inject(fragment: CurrentShoppingListFragment)

    fun inject(fragment: ArchivedShoppingListFragment)

    fun inject(fragment: ShoppingListDetailsFragment)

}
