package com.panchal.vivek.moviemania.di.module

import android.content.Context
import com.panchal.vivek.moviemania.BuildConfig
import com.panchal.vivek.moviemania.MyApplication
import com.panchal.vivek.moviemania.data.remote.NetworkService
import com.panchal.vivek.moviemania.data.remote.Networking
import com.panchal.vivek.moviemania.di.ApplicationContext
import com.panchal.vivek.moviemania.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "dummy_db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
            Networking.create(
                    BuildConfig.API_KEY,
                    BuildConfig.BASE_URL,
                    application.cacheDir,
                    10 * 1024 * 1024 // 10MB
            )
}
