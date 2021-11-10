package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentMatchesBinding
import by.aderman.tottenhamhotspurfc.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class MatchesFragment : Fragment() {

    private lateinit var binding: FragmentMatchesBinding
    private val fixturesFragment by inject<FixturesFragment>()
    private val resultsFragment by inject<ResultsFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchesBinding.inflate(inflater, container, false)

        val fragments = arrayListOf(fixturesFragment, resultsFragment)

        val viewPagerAdapter =
            ViewPagerAdapter(fragments, childFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.fragment_matches_fixtures_tab)
                    1 -> tab.text = getString(R.string.fragment_matches_results_tab)
                }
            }.attach()
        }

        return binding.root
    }
}