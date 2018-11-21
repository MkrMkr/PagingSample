package com.raywenderlich.android.redditclone

import android.app.Application
import com.raywenderlich.android.redditclone.database.DataRepository
import com.raywenderlich.android.redditclone.database.RedditDb

class BasicApp : Application() {

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(RedditDb.create(this))
    }
}