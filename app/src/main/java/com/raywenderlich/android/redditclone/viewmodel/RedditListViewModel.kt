package com.raywenderlich.android.redditclone.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.raywenderlich.android.redditclone.BasicApp
import com.raywenderlich.android.redditclone.networking.RedditPost

public class RedditListViewModel(application: Application) : AndroidViewModel(application) {

    var postsPagedList: LiveData<PagedList<RedditPost>> //saving created LiveData<PagedList> to field in view model causes
    //that after rotation data isn't downloading again

    init {
        postsPagedList = getRedditPostsPagedList(application)
    }

    private fun getRedditPostsPagedList(application: Application): LiveData<PagedList<RedditPost>> {
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .build()
        val factory = (application as BasicApp).getRepository().redditPostsFactory
        return LivePagedListBuilder<String, RedditPost>(factory, config).build()
    }

}