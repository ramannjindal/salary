package com.raman.salary

import android.app.Application
import android.content.Context
import io.paperdb.BuildConfig
import io.paperdb.Paper

class SalaryApp : Application() {
    var isDebuggingEnabled: Boolean = false

    companion object {
        private var sInstance: SalaryApp? = null
        fun getInstance(): SalaryApp? {
            return sInstance;
        }

        private lateinit var appContext: Context

        @JvmStatic
        fun getGlobalAppContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        appContext = applicationContext
        sInstance = this
        isDebuggingEnabled = BuildConfig.DEBUG
    }
}