import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.news_project.R
import com.example.news_project.api_service.NewsApi
import com.example.news_project.repository.Repository
import com.example.news_project.view.GalleyFragment
import com.example.news_project.view_model.NewsViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NewsFeedFragment : Fragment() {
    private val viewModel by viewModels<NewFeedViewModel> {
        val repository = Repository(NewsApi())
        NewsViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_news, container, false)
        val viewPager = rootView.findViewById<ViewPager2>(R.id.view_pager)

        viewModel.newsFeed.observe(viewLifecycleOwner, Observer { newsFeed ->
            val newsList = newsFeed.items

            val storyNewsList = newsList.filter { it.contentType == "story" }
            val overviewNewsList = newsList.filter { it.contentType == "overview" }
            val videoNewsList = newsList.filter { it.contentType == "video" }
            val galleryNewsList = newsList.filter { it.contentType == "gallery" }

            val tabLayout = rootView.findViewById<TabLayout>(R.id.tab_layout)

            val fragments = listOf(
                StoryFragment(storyNewsList),
                OverviewFragment(overviewNewsList),
                VideoFragment(videoNewsList),
                GalleyFragment(galleryNewsList)
            )

            val adapter = NewsPagerAdapter(this, fragments)
            viewPager.adapter = adapter


            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.story_fragment)
                    1 -> getString(R.string.overview_fragment)
                    2 -> getString(R.string.video_fragment)
                    3 -> getString(R.string.gallery_fragment)
                    else -> ""
                }
            }.attach()
        })
        return rootView
    }

}
