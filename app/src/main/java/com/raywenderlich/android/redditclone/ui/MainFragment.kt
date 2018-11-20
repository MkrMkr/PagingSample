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

class MainFragment : Fragment() {

    private lateinit var redditAdapter: RedditAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        redditAdapter = RedditAdapter()
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
        ViewModelProviders.of(this).get(RedditListViewModel::class.java)
                .postsPagedList.observe(this, Observer { redditPosts ->
            if (redditPosts != null) {
                redditAdapter.submitList(redditPosts)
            }
        })
    }

    companion object {
        const val TAG = "tag"
    }

}
