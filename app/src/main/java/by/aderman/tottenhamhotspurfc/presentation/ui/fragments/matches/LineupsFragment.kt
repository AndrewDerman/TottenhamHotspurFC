package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.databinding.FragmentLineupsBinding
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.AwayLineupAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.HomeLineupAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel

class LineupsFragment : Fragment() {

    private lateinit var binding: FragmentLineupsBinding
    private val viewModel by lazy { requireParentFragment().getViewModel<FixturesViewModel>() }
    private val homeStartXIAdapter by inject<HomeLineupAdapter>()
    private val awayStartXIAdapter by inject<AwayLineupAdapter>()
    private val homeSubstitutesAdapter by inject<HomeLineupAdapter>()
    private val awaySubstitutesAdapter by inject<AwayLineupAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLineupsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerViews()
        observeData()
        return binding.root
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, { result ->
            val lineups = result.data?.lineups
            if (!lineups.isNullOrEmpty()) {
                viewModel.changeLineupsStatus(true)
                homeStartXIAdapter.differ.submitList(lineups[0].startXI)
                awayStartXIAdapter.differ.submitList(lineups[1].startXI)
                homeSubstitutesAdapter.differ.submitList(lineups[0].substitutes)
                awaySubstitutesAdapter.differ.submitList(lineups[1].substitutes)
                binding.homeLineup = lineups[0]
                binding.awayLineup = lineups[1]
            } else {
                viewModel.changeLineupsStatus(false)
            }
        })
    }

    private fun setRecyclerViews() {
        with(binding) {
            recyclerViewStartHome.adapter = homeStartXIAdapter
            recyclerViewStartHome.layoutManager = LinearLayoutManager(requireContext())

            recyclerViewStartAway.adapter = awayStartXIAdapter
            recyclerViewStartAway.layoutManager = LinearLayoutManager(requireContext())

            recyclerViewSubstitutesHome.adapter = homeSubstitutesAdapter
            recyclerViewSubstitutesHome.layoutManager = LinearLayoutManager(requireContext())

            recyclerViewSubstitutesAway.adapter = awaySubstitutesAdapter
            recyclerViewSubstitutesAway.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}