package com.urmwsk.core.android

import android.os.Bundle
import android.support.v4.app.Fragment
import com.urmwsk.core.R

abstract class BaseFragmentActivity : BaseActivity() {

    private val fragmentKey = "fragmentKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (savedInstanceState != null) {
//            supportFragmentManager.getFragment(savedInstanceState, fragmentKey)
//        } else {
            if (supportFragmentManager.findFragmentById(R.id.container) == null) {
                supportFragmentManager.beginTransaction().replace(R.id.container, instantiateFragment()).commit()
            }
//        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        supportFragmentManager.putFragment(outState, fragmentKey, supportFragmentManager.findFragmentById(R.id.container))
//    }

    abstract fun instantiateFragment(): Fragment
}
