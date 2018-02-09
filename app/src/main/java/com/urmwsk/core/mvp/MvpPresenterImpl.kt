package com.urmwsk.core.mvp

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference


abstract class MvpPresenterImpl<VIEW : MvpView> : MvpPresenter<VIEW> {

    private var viewRef: WeakReference<VIEW>? = null
    protected var disposable = CompositeDisposable()

    val view: VIEW?
        get() {
            if (viewRef == null) {
                throw NullPointerException("getView() called when viewRef is null. Ensure bindView(View view) is called first.")
            }
            return viewRef?.get()
        }

    override val isViewBound: Boolean
        get() = viewRef != null

    override fun bindView(view: VIEW) {
        disposable = CompositeDisposable()
        viewRef = WeakReference(view)
    }

    override fun unbindView() {
        viewRef = null
        disposable.clear()
    }

}
