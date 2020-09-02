package com.panchal.vivek.moviemania.di.component

import com.panchal.vivek.moviemania.MainActivity
import com.panchal.vivek.moviemania.di.ActivityScope
import com.panchal.vivek.moviemania.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}
