package com.urmwsk.feature.shoppinglist

import com.urmwsk.core.R
import com.urmwsk.core.android.BaseFragmentActivity
import com.urmwsk.feature.shoppinglist.current.ShoppingListFragment

/*
    User stories

    Adding shopping list: As a user I want to easily add a new shopping list
    Removing shopping list: As a user I want to be able to delete any shopping list and also be prompted when all shopping list items are purchased
    Displaying shopping lists: As a user I would like to see my current shopping lists
    Removing shopping item: As a user I want to be able add shopping list item easily
    Removing shopping item: As a user I want to be able remove a shopping list item easily
    Marking shopping item: As a user I want to be able mark list item as purchased or to buy
    As a user I would like to see my selected shopping list items

 */
class MainActivity : BaseFragmentActivity() {
    override fun instantiateFragment() = ShoppingListFragment.newInstance()
    override fun layoutId() = R.layout.activity_main
}