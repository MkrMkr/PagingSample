package com.raywenderlich.android.redditclone.ui


import android.arch.lifecycle.Observer
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.redditclone.R
import com.raywenderlich.android.redditclone.RedditAdapter
import com.raywenderlich.android.redditclone.RedditDataSource
import com.raywenderlich.android.redditclone.networking.RedditPost
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    private lateinit var redditAdapter: RedditAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        redditAdapter = RedditAdapter()
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .build()

        //2
        val liveData = initializedPagedListBuilder(config).build()

        //3
        liveData.observe(this, Observer<PagedList<RedditPost>> { pagedList ->
            Log.i("testTest","redditAdapter->submitList "+pagedList)
            redditAdapter.submitList(pagedList)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redditList.adapter = redditAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //ViewModelProviders.of(this)
    }


    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, RedditPost> {


        val dataSourceFactory = object : DataSource.Factory<String, RedditPost>() {
            override fun create(): DataSource<String, RedditPost> {
                return RedditDataSource()
            }
        }
        return LivePagedListBuilder<String, RedditPost>(dataSourceFactory, config)

//        val database = RedditDb.create(this)
//        return LivePagedListBuilder<Int, RedditPost>(database.postDao().posts(), config)
//                .apply {
//                    setBoundaryCallback(RedditBoundaryCallback(database))
//                };
    }



    companion object {
        const val TAG = "tag"
    }

}
