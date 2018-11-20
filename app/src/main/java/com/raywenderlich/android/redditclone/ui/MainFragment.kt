package com.raywenderlich.android.redditclone.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.redditclone.R
import com.raywenderlich.android.redditclone.RedditAdapter
import com.raywenderlich.android.redditclone.viewmodel.RedditListViewModel
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

        //2
        //val liveData = initializedPagedListBuilder(config).build()

        //3
//        liveData.observe(this, Observer<PagedList<RedditPost>> { pagedList ->
//            Log.i("testTest","redditAdapter->submitList "+pagedList)
//            redditAdapter.submitList(pagedList)
//        })
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
        var redditListViewModel = ViewModelProviders.of(this).get(RedditListViewModel::class.java)
        redditListViewModel.getRedditPostsPagedList().observe(this, Observer { redditPosts ->
            if (redditPosts != null) {
                redditAdapter.submitList(redditPosts)
            }
        })
    }


    companion object {
        const val TAG = "tag"
    }

}
