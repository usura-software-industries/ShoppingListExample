package com.urmwsk.feature.shoppinglist.current.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.urmwsk.common.utils.KeyboardUtils
import com.urmwsk.core.R
import com.urmwsk.core.android.BaseDialog
import kotlinx.android.synthetic.main.dialog_add_shopping_list.*

class AddShoppingListDialog : BaseDialog() {

    private var callback: AddShoppingListDialogCallback? = null

    companion object {
        fun show(manager: FragmentManager, target: Fragment) {
            val dialog = AddShoppingListDialog()
            dialog.setTargetFragment(target, 100)
            dialog.show(manager, "addShoppingList")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //intentional NPE possibility to tell when callback not implemented
        callback = targetFragment as AddShoppingListDialogCallback

        cancel.setOnClickListener({
            KeyboardUtils().hideKeyboard(title)
            dismiss()
        })
        add.setOnClickListener({ finishAdding() })

        title.setOnEditorActionListener({ _, code, _ ->
            var isActionDone = false
            if (code == EditorInfo.IME_ACTION_DONE) {
                finishAdding()
                isActionDone = true
            }
            isActionDone
        })

        if (activity != null) {
            KeyboardUtils().showKeyboard(activity!!)
        }
    }

    override fun onDestroyView() {
        callback = null
        setTargetFragment(null, 0)
        super.onDestroyView()
    }

    private fun finishAdding() {
        callback?.confirmAddShoppingList(if (title.text.isEmpty()) getString(R.string.shopping_list_default_title) else title.text.toString())
        KeyboardUtils().hideKeyboard(title)
        dismiss()
    }

    override val layoutId: Int
        get() = R.layout.dialog_add_shopping_list

}

interface AddShoppingListDialogCallback {
    fun confirmAddShoppingList(title: String)
}