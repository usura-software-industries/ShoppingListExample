package com.urmwsk.common.injection.components

import com.urmwsk.common.injection.annotation.PerFragment
import com.urmwsk.common.injection.modules.FragmentModule
import com.urmwsk.feature.shoppinglist.current.ShoppingListFragment
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {

    fun inject(fragment: ShoppingListFragment)

    fun inject(fragment: ShoppingListDetailsFragment)

}
