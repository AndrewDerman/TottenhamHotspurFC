package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentStatsBinding
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.StatsAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.LinearMarginItemDecoration
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.ext.android.inject

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private val viewModel by lazy { requireParentFragment().getViewModel<FixturesViewModel>() }
    private val statsAdapter by inject<StatsAdapter>()
    private val itemDecoration by inject<LinearMarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerView()
        observeData()
        return binding.root
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, { result ->
            val statistics = result?.data?.statistics
            if (!statistics.isNullOrEmpty()) {
                statsAdapter.differ.submitList(statistics)
                viewModel.changeStatisticStatus(true)
            } else {
                viewModel.changeStatisticStatus(false)
            }
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = statsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(itemDecoration.also {
                it.margin = resources.getDimensionPixelSize(
                    R.dimen.fragment_stats_items_margin
                )
            })
        }
    }
}