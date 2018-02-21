package com.urmwsk.feature.shoppinglist.details.view

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.urmwsk.common.utils.KeyboardUtils
import com.urmwsk.core.R
import com.urmwsk.core.android.BaseDialog
import kotlinx.android.synthetic.main.dialog_add_shopping_list_element.*

class AddShoppingListElementDialog : BaseDialog() {

    private var callback: AddShoppingListElementDialogCallback? = null
    private var textWatcher = object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            add.isEnabled = !text!!.isEmpty()
        }

        override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    companion object {
        fun show(manager: FragmentManager) {
            val dialog = AddShoppingListElementDialog()
            dialog.show(manager, "addShoppingList")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback = parentFragment as AddShoppingListElementDialogCallback
        add.isEnabled = false
        title.addTextChangedListener(textWatcher)

        title.setOnEditorActionListener({ _, code, _ ->
            var isActionDone = false
            if (code == EditorInfo.IME_ACTION_DONE && !title.text.isEmpty()) {
                finishAdding()
                isActionDone = true
            }
            isActionDone
        })

        cancel.setOnClickListener({
            KeyboardUtils().hideKeyboard(title)
            dismiss()
        })

        //intentional NPE possibility to tell when callback not implemented
        add.setOnClickListener({ finishAdding() })

        if (activity != null) {
            KeyboardUtils().showKeyboard(activity!!)
        }
    }

    override fun onDestroyView() {
        title.removeTextChangedListener(textWatcher)
        callback = null
        setTargetFragment(null, 0)
        super.onDestroyView()
    }

    private fun finishAdding() {
        callback!!.confirmAddShoppingElement(
                if (title.text.isEmpty()) getString(R.string.shopping_list_default_title) else title.text.toString())
        KeyboardUtils().hideKeyboard(title)
        dismiss()
    }

    override val layoutId: Int
        get() = R.layout.dialog_add_shopping_list_element

}

interface AddShoppingListElementDialogCallback {
    fun confirmAddShoppingElement(title: String)
}