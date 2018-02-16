package com.urmwsk.core.stub

import com.urmwsk.common.injection.components.AppComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(StubAppModule::class), (StubDataModule::class)])
interface StubAppComponent : AppComponent