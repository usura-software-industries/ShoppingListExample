package com.urmwsk.feature.shoppinglist.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.urmwsk.common.view.setupHideWithRecyclerview
import com.urmwsk.core.R
import com.urmwsk.core.mvp.MvpFragment
import com.urmwsk.feature.shoppinglist.details.view.AddShoppingListElementDialog
import com.urmwsk.feature.shoppinglist.details.view.AddShoppingListElementDialogCallback
import kotlinx.android.synthetic.main.fragment_shopping_list_details.*


class ShoppingListDetailsFragment : MvpFragment<ShoppingListDetailsContract.View, ShoppingListDetailsContract.Presenter>(), ShoppingListDetailsContract.View, AddShoppingListElementDialogCallback {

    private val adapter = FastItemAdapter<IItem<*, *>>()

    companion object {

        private const val ID_KEY = "idKey"

        fun newInstance(id: String): ShoppingListDetailsFragment {
            val fragment = ShoppingListDetailsFragment()
            val args = Bundle()
            args.putString(ID_KEY, id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupList()
        addShoppingListElement.setOnClickListener({ showAddElementDialog() })

        if (arguments != null) {
            presenter.setData(arguments!!.getString(ID_KEY))
        }
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(detailsToolbar)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupList() {
        shoppingList.adapter = adapter
        shoppingList.layoutManager = LinearLayoutManager(activity)
        addShoppingListElement.setupHideWithRecyclerview(shoppingList)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onDestroyView() {
        adapter.withOnClickListener(null)
        addShoppingListElement.setOnClickListener(null)
        super.onDestroyView()
    }

    override fun setTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    override fun setList(items: List<IItem<*, *>>) {
        adapter.set(items)
    }

    override fun showAddElementDialog() {
        AddShoppingListElementDialog.show(childFragmentManager)
    }

    override fun confirmAddShoppingElement(title: String) {
        presenter.addShoppingElement(title)
    }

    override fun closeScreen() {
        activity?.finish()
    }

    override fun layoutId() = R.layout.fragment_shopping_list_details

    override fun setupInjection() {
        getFragmentComponent()?.inject(this)
    }
}