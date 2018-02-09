package com.urmwsk.common.injection.components

import com.urmwsk.common.injection.modules.AppModule
import com.urmwsk.common.injection.modules.DataModule
import com.urmwsk.common.injection.modules.FragmentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (DataModule::class)])
interface AppComponent {

    fun with(fragmentModule: FragmentModule): FragmentComponent
}