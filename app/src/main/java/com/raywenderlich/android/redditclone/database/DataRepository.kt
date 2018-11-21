package com.raywenderlich.android.redditclone.database

import com.raywenderlich.android.redditclone.RedditBoundaryCallback

class DataRepository(redditDb: RedditDb) {

    val redditPostsFromDbFactory = redditDb.postDao().posts() //Room is able to return DataSource.Factory for us
    val boundaryCallback = RedditBoundaryCallback(redditDb)

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(redditDb: RedditDb): DataRepository {
            if (instance == null) {
                synchronized(DataRepository::class.java) {
                    if (instance == null) {
                        instance = DataRepository(redditDb)
                    }
                }
            }
            return instance!!
        }
    }

}