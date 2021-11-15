package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.aderman.tottenhamhotspurfc.databinding.FragmentStatsBinding
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private val viewModel by lazy { requireParentFragment().getViewModel<FixturesViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeData()
        return binding.root
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, { result ->
            val statistic = result.data?.statistics
            if (!statistic.isNullOrEmpty()) {
                binding.homeStats = statistic[0]
                binding.awayStats = statistic[1]
                viewModel.changeStatisticStatus(true)
            } else {
                viewModel.changeStatisticStatus(false)
            }
        })
    }
}