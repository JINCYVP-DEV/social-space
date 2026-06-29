package com.nj.space

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.FirebaseApp

class FirebaseInitializer:Initializer<FirebaseApp> {
    override fun create(context: Context): FirebaseApp {
        return FirebaseApp.initializeApp(context)!!
    }

    override fun dependencies()= emptyList<Class<Initializer<*>>>()

}


class AnalyticsInitializer : Initializer<Analytics> {
    override fun create(context: Context): Analytics {
        return Analytics.init(context)
    }

    override fun dependencies() =
        listOf(FirebaseInitializer::class.java)
}


class Analytics {
    companion object {
        fun init(context: Context): Analytics {
            return Analytics()
        }
    }
}