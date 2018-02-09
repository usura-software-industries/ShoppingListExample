package com.urmwsk.core.mvp

interface MvpPresenter<in VIEW : MvpView> {

    val isViewBound: Boolean

    fun bindView(view: VIEW)

    fun unbindView()
}
