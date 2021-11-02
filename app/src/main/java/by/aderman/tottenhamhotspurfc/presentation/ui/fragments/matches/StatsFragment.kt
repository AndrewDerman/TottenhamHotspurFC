package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.aderman.tottenhamhotspurfc.databinding.FragmentStatsBinding
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private val viewModel by viewModel<FixturesViewModel> { parametersOf() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadData()
        observeData()

        return binding.root
    }

    private fun loadData() {
        arguments?.let { viewModel.getFixtureInfo(it.getInt(Constants.FRAGMENTS_ID_KEY)) }
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, {
            val statistic = it.data?.statistics
            if (!statistic.isNullOrEmpty()) {
                binding.homeStats = statistic[0]
                binding.awayStats = statistic[1]
                viewModel.changeStatisticStatus(true)
            } else {
                viewModel.changeStatisticStatus(false)
            }
        })
    }

    companion object {
        fun getNewInstance(id: Int): StatsFragment {
            val args = Bundle().also { it.putInt(Constants.FRAGMENTS_ID_KEY, id) }
            return StatsFragment().also { it.arguments = args }
        }
    }
}