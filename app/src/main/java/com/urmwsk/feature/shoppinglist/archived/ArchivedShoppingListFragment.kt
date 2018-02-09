package com.urmwsk.feature.shoppinglist.archived

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.urmwsk.core.R
import com.urmwsk.core.mvp.MvpFragment
import com.urmwsk.feature.shoppinglist.details.ShoppingListDetailsActivity
import kotlinx.android.synthetic.main.fragment_archived_shopping_list.*

class ArchivedShoppingListFragment : MvpFragment<ArchivedShoppingListContract.View, ArchivedShoppingListContract.Presenter>(), ArchivedShoppingListContract.View {
    private val adapter = FastItemAdapter<IItem<*, *>>()

    companion object {
        fun newInstance(): ArchivedShoppingListFragment = ArchivedShoppingListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        shoppingList.adapter = adapter
        shoppingList.layoutManager = LinearLayoutManager(activity)
        adapter.withSelectable(true)
        adapter.withOnClickListener({ _, _, _, position ->
            presenter.itemSelected(position)
            true
        })
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onDestroyView() {
        adapter.withOnClickListener(null)
        super.onDestroyView()
    }

    override fun showDetails(id: String) {
        activity?.let { ShoppingListDetailsActivity.startActivity(it, id, false) }
    }

    override fun setList(items: List<IItem<*, *>>) {
        adapter.set(items)
    }

    override fun layoutId() = R.layout.fragment_archived_shopping_list

    override fun setupInjection() {
        getFragmentComponent()?.inject(this)
    }

}
