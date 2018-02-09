package com.urmwsk.feature.shoppinglist

import android.os.Bundle
import android.support.v4.app.Fragment
import com.urmwsk.core.R
import com.urmwsk.core.android.BaseActivity
import com.urmwsk.feature.shoppinglist.archived.ArchivedShoppingListFragment
import com.urmwsk.feature.shoppinglist.current.CurrentShoppingListFragment
import kotlinx.android.synthetic.main.activity_main.*


//todo README using bottom navigation because I believe it is most fitting, despite not having the recommended 3 minimum items
class MainActivity : BaseActivity() {

    private var currentItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_shopping_list -> if (currentItem != item.itemId) selectedFragment = CurrentShoppingListFragment.newInstance()
                R.id.navigation_shopping_archive -> if (currentItem != item.itemId) selectedFragment = ArchivedShoppingListFragment.newInstance()
            }
            currentItem = item.itemId

            if (selectedFragment != null) supportFragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit()
            true
        }

        bottomNavigation.selectedItemId = R.id.navigation_shopping_list
    }

    override fun layoutId() = R.layout.activity_main
}