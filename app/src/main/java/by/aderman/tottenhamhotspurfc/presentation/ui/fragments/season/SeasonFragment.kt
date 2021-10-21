package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.season

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentSeasonBinding
import by.aderman.tottenhamhotspurfc.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SeasonFragment : Fragment() {

    private lateinit var binding: FragmentSeasonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeasonBinding.inflate(inflater, container, false)

        val fragments = arrayListOf(
            TableFragment(),
            GoalsFragment(),
            AssistsFragment()
        )

        val viewPagerAdapter =
            ViewPagerAdapter(fragments, childFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.fragment_season_league_table_tab)
                    1 -> tab.text = getString(R.string.fragment_season_top_goals_tab)
                    2 -> tab.text = getString(R.string.fragment_season_top_assists_tab)
                }
            }.attach()
        }

        return binding.root
    }
}