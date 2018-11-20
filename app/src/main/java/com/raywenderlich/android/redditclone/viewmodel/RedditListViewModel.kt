package com.raywenderlich.android.redditclone.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.raywenderlich.android.redditclone.BasicApp
import com.raywenderlich.android.redditclone.networking.RedditPost

public class RedditListViewModel(application: Application) : AndroidViewModel(application) {

    private var reditPosts: DataSource.Factory<String, RedditPost> = (application as BasicApp).getRepository().redditPostsFactory

    fun getRedditPostsPagedList(): LiveData<PagedList<RedditPost>> {
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .build()
        return LivePagedListBuilder<String, RedditPost>(reditPosts, config).build()
    }

}