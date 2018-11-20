package com.raywenderlich.android.redditclone.database

import android.arch.paging.DataSource
import com.raywenderlich.android.redditclone.RedditDataSource
import com.raywenderlich.android.redditclone.networking.RedditPost

class DataRepository() {

    val redditPostsFactory = object : DataSource.Factory<String, RedditPost>() {
        override fun create(): DataSource<String, RedditPost> {
            return RedditDataSource()
        }
    }

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(): DataRepository {
            if (instance == null) {
                synchronized(DataRepository::class.java) {
                    if (instance == null) {
                        instance = DataRepository()
                    }
                }
            }
            return instance!!
        }
    }

}