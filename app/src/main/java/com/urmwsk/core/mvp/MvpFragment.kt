@file:Suppress("UNCHECKED_CAST")

package com.urmwsk.core.mvp

import android.os.Bundle
import android.view.View
import com.urmwsk.core.android.BaseFragment
import javax.inject.Inject


abstract class MvpFragment<in VIEW : MvpView, PRESENTER : MvpPresenter<VIEW>> : BaseFragment(), MvpView {

    @Inject
    protected lateinit var presenter: PRESENTER

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this as VIEW)
    }

    override fun onDestroyView() {
        presenter.unbindView()
        super.onDestroyView()
    }
}
