package com.panchal.vivek.moviemania.di.component

import android.content.Context
import com.mindorks.bootcamp.demo.utils.NetworkHelper
import com.panchal.vivek.moviemania.MyApplication
import com.panchal.vivek.moviemania.data.local.DatabaseService
import com.panchal.vivek.moviemania.data.remote.NetworkService
import com.panchal.vivek.moviemania.di.ApplicationContext
import com.panchal.vivek.moviemania.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getCompositeDisposable(): CompositeDisposable

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getNetworkHelper(): NetworkHelper
}
