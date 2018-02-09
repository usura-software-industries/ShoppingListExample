package com.urmwsk.common.view

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View

fun FloatingActionButton.setupHideWithRecyclerview(recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0 && visibility == View.VISIBLE) {
                hide()
            } else if (dy < 0 && visibility != View.VISIBLE) {
                show()
            }
        }
    })
}