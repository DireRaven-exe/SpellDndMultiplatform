package com.spelldnd

import android.app.Application
import com.google.android.datatransport.BuildConfig
import com.spelldnd.shared.di.initKoin
import com.spelldnd.shared.utils.ContextUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class SpellDndApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ContextUtils.setContext(context = this)

        initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@SpellDndApplication)
        }
    }
}