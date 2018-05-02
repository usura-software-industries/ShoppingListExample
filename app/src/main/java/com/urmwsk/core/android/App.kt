package com.urmwsk.core.android

import android.app.Application
import com.urmwsk.common.injection.components.AppComponent
import com.urmwsk.common.injection.components.DaggerAppComponent
import com.urmwsk.common.injection.modules.AppModule
import io.realm.Realm
import timber.log.Timber


class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Realm.init(this)
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}
