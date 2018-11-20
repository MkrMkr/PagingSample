package com.raywenderlich.android.redditclone

import android.app.Application
import com.raywenderlich.android.redditclone.database.DataRepository

class BasicApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance()
    }
}