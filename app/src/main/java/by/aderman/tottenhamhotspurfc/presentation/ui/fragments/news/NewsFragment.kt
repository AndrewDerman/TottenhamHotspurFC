package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentNewsBinding
import by.aderman.tottenhamhotspurfc.presentation.adapters.ViewPagerAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.news.NewsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val newsAdapter by inject<NewsAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        val fragments = arrayListOf(
            LatestNewsFragment(),
            SavedNewsFragment()
        )

        val viewPagerAdapter =
            ViewPagerAdapter(fragments, childFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.fragment_news_latest_tab)
                    1 -> tab.text = getString(R.string.fragment_news_saved_tab)
                }
            }.attach()
        }

        return binding.root
    }
}