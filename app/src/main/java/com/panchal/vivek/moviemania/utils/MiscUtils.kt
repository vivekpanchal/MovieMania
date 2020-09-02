package com.panchal.vivek.moviemania.utils

import android.content.Context
import android.net.ConnectivityManager
import android.transition.ChangeBounds
import android.transition.Transition
import android.view.animation.DecelerateInterpolator

object MiscUtils {

    fun enterTransition(): Transition {
        val bounds = ChangeBounds()
        bounds.duration = 500000
        return bounds
    }

    fun returnTransition(): Transition {
        val bounds = ChangeBounds()
        bounds.interpolator = DecelerateInterpolator()
        bounds.duration = 500000
        return bounds
    }

    //to check the connectivity
    @JvmStatic
    fun getConnectivityStatus(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}