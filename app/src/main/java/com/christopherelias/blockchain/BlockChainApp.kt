package com.christopherelias.blockchain

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@HiltAndroidApp
class BlockChainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}