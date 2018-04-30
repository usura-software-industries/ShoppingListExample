package com.urmwsk.feature.shoppinglist.details

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.urmwsk.core.R
import com.urmwsk.core.android.BaseFragmentActivity

class ShoppingListDetailsActivity : BaseFragmentActivity() {

    //there are better ways of doing this f.e. anko commons intents
    //but its not available for fragments so I'd rather keep it consistent
    companion object {

        private const val ID_KEY = "idKey"

        fun startActivity(context: Context, id: String, canEdit: Boolean) {
            val intent = Intent(context, ShoppingListDetailsActivity::class.java)
            intent.putExtra(ID_KEY, id)
            context.startActivity(intent)
        }
    }

    override fun instantiateFragment(): Fragment {
        return ShoppingListDetailsFragment.newInstance(intent.getStringExtra(ID_KEY))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun layoutId() = R.layout.activity_shopping_list_details
}