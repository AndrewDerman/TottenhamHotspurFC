package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentFixtureInfoBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.adapters.ViewPagerAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.android.ext.android.inject

class FixtureInfoFragment : Fragment() {

    private lateinit var binding: FragmentFixtureInfoBinding
    private val viewModel by viewModel<FixturesViewModel> { parametersOf() }
    private val eventsFragment by inject<EventsFragment>()
    private val statsFragment by inject<StatsFragment>()
    private val lineupsFragment by inject<LineupsFragment>()
    private val args by navArgs<FixtureInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFixtureInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadData()
        observeData()

        val fragments = arrayListOf(
            eventsFragment,
            statsFragment,
            lineupsFragment
        )

        val viewPagerAdapter =
            ViewPagerAdapter(fragments, childFragmentManager, lifecycle)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.fragment_fixture_info_events_tab)
                    1 -> tab.text = getString(R.string.fragment_fixture_info_stats_tab)
                    2 -> tab.text = getString(R.string.fragment_fixture_info_lineups_tab)
                }
            }.attach()
        }

        binding.swipeRefreshLayout.setOnRefreshListener { loadData() }

        return binding.root
    }

    private fun loadData() {
        viewModel.getFixtureInfo(args.currentFixtureId)
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    binding.fixture = it.data
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Result.Error -> {
                    it.message?.let { error -> showSnackbar(binding.root, error) }
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Result.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }
}