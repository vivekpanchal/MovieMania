/*
 * Copyright (C) 2018 The Android Popular Movies Project made under Udacity Nanodegree Course
 * Author Pawan Kumar Sharma
 * All Rights Reserved
 */
package com.panchal.vivek.moviemania.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * This class is used to execute database operations
 */
class AppExecutors private constructor(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {
    /**
     * Disk Io
     * @return Executor
     */
    fun diskIO(): Executor {
        return diskIO
    }

    /**
     * Main Thread
     * @return Executor
     */
    fun mainThread(): Executor {
        return mainThread
    }

    /**
     * networkIO
     * @return Executor
     */
    fun networkIO(): Executor {
        return networkIO
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: AppExecutors? = null

        /**
         * To get Instance of this class
         * @return instance
         */
        @JvmStatic
        val instance: AppExecutors?
            get() {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        sInstance = AppExecutors(Executors.newSingleThreadExecutor(),
                                Executors.newFixedThreadPool(3),
                                MainThreadExecutor())
                    }
                }
                return sInstance
            }
    }
}