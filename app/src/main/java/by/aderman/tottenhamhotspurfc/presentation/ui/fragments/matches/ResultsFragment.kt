package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentResultsBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureStatus
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.FixturesAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.LinearMarginItemDecoration
import by.aderman.tottenhamhotspurfc.utils.getCurrentDateForApiRequest
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ResultsFragment : Fragment() {

    private lateinit var binding: FragmentResultsBinding
    private val viewModel by viewModel<FixturesViewModel> { parametersOf() }
    private val resultsAdapter by inject<FixturesAdapter>()
    private val itemDecoration by inject<LinearMarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerView()
        loadData()
        observeSavedResults()

        resultsAdapter.setOnItemClickListener {
            findNavController().navigate(
                MatchesFragmentDirections.actionMatchesFragmentToFixtureInfoFragment(it)
            )
        }

        binding.swipeRefreshLayout.setOnRefreshListener { loadData() }

        return binding.root
    }

    private fun loadData() = viewModel.getSavedResults()

    private fun observeSavedResults() {
        viewModel.savedResultsLiveData.observe(viewLifecycleOwner, { savedResults ->
            if (!savedResults.isNullOrEmpty()) {
                resultsAdapter.differ.submitList(savedResults.reversed())
                viewModel.changeResponseReceivedStatus(true)
                binding.swipeRefreshLayout.isRefreshing = false
            } else {
                viewModel.getResults(getCurrentDateForApiRequest())
                observeResultsFromRemote()
            }
        })
    }

    private fun observeResultsFromRemote() {
        viewModel.resultsLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    val fixtures = it.data?.reversed()?.filter { fixture ->
                        fixture.status.shortValue == FixtureStatus.FT.name
                                || fixture.status.shortValue == FixtureStatus.AET.name
                                || fixture.status.shortValue == FixtureStatus.PEN.name
                                || fixture.status.shortValue == FixtureStatus.AWD.name
                                || fixture.status.shortValue == FixtureStatus.WO.name
                    }
                    resultsAdapter.differ.submitList(fixtures)
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false

                    if (fixtures != null) {
                        for (fixture in fixtures)
                            viewModel.saveResult(fixture)
                    }
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

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = resultsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(itemDecoration.also {
                it.margin = resources.getDimensionPixelSize(
                    R.dimen.fragment_news_recycler_margin
                )
            })
        }
    }
}