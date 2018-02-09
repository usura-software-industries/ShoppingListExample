package com.urmwsk.core.android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.urmwsk.common.injection.components.FragmentComponent
import com.urmwsk.common.injection.modules.FragmentModule
import com.urmwsk.core.android.App.Companion.appComponent

abstract class BaseFragment : Fragment() {

    private var fragmentComponent: FragmentComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(layoutId(), container, false)
        setupInjection()
        return v
    }

    protected fun getFragmentComponent(): FragmentComponent? {
        if (fragmentComponent == null) {
            fragmentComponent = appComponent.with(FragmentModule())
        }
        return fragmentComponent
    }

    protected abstract fun layoutId(): Int

    protected abstract fun setupInjection()
}
