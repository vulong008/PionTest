package com.example.news_project.view

import NewsFeedAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_project.R
import com.example.news_project.model.NewsFeedItem
import com.example.news_project.model.NewsItemClickListener

class GalleyFragment(private val newsList: List<NewsFeedItem>) : Fragment(), NewsItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_gallery, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        newsAdapter = NewsFeedAdapter(newsList, this)
        recyclerView.adapter = newsAdapter

        return rootView
    }

    override fun onNewsItemClick(news: NewsFeedItem) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(news.title)
            .setMessage(news.description)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }
}
