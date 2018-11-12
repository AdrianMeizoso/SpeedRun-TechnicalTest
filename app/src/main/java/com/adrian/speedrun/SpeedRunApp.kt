package com.adrian.speedrun

import com.adrian.speedrun.common.injection.ApplicationComponent
import com.adrian.speedrun.common.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SpeedRunApp : DaggerApplication() {

    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent.inject(this)
        return applicationComponent
    }
}