package com.urmwsk.feature.shoppinglist.current

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.urmwsk.common.view.setupHideWithRecyclerview
import com.urmwsk.core.R
import com.urmwsk.core.mvp.MvpFragment
import com.urmwsk.feature.shoppinglist.current.views.AddShoppingListDialog
import com.urmwsk.feature.shoppinglist.current.views.AddShoppingListDialogCallback
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsActivity
import kotlinx.android.synthetic.main.fragment_current_shopping_list.*

class ShoppingListFragment : MvpFragment<ShoppingListContract.View, ShoppingListContract.Presenter>(), ShoppingListContract.View, AddShoppingListDialogCallback {

    private val adapter = FastItemAdapter<IItem<*, *>>()

    companion object {
        fun newInstance(): ShoppingListFragment = ShoppingListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        addShoppingList.setOnClickListener { showAddListDialog() }
    }

    private fun setupList() {
        shoppingList.adapter = adapter
        shoppingList.layoutManager = LinearLayoutManager(activity!!)
        adapter.withSelectable(true)
        addShoppingList.setupHideWithRecyclerview(shoppingList)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onDestroyView() {
        //kinda missing [weak self] from swift :(
        adapter.withOnClickListener(null)
        addShoppingList.setOnClickListener(null)
        super.onDestroyView()
    }

    override fun showDetails(id: String) {
        activity?.let { ShoppingListDetailsActivity.startActivity(it, id, true) }
    }

    override fun showAddListDialog() {
        AddShoppingListDialog.show(childFragmentManager)
    }

    override fun confirmAddShoppingList(title: String) {
        presenter.addNewShoppingList(title)
    }

    override fun setList(items: List<IItem<*, *>>) {
        adapter.set(items)
    }

    override fun layoutId() = R.layout.fragment_current_shopping_list

    override fun setupInjection() {
        getFragmentComponent()?.inject(this)
    }

}
