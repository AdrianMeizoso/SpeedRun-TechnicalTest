package com.adrian.speedrun.common.injection

import com.adrian.speedrun.main.view.DetailFragment
import com.adrian.speedrun.main.view.ListFragment
import com.adrian.speedrun.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorBuilder {

    @ContributesAndroidInjector
    @PerActivity
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    @PerFragment
    abstract fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun bindDetailFragment(): DetailFragment
}