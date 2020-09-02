package com.panchal.vivek.moviemania.di.component

import com.panchal.vivek.moviemania.di.module.FragmentModule
import com.panchal.vivek.moviemania.di.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

//    fun inject(fragment: HomeFragment)
}
