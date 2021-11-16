package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.season

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentGoalsBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.GoalsAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.season.SeasonViewModel
import by.aderman.tottenhamhotspurfc.utils.TableItemDecoration
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GoalsFragment : Fragment() {

    private lateinit var binding: FragmentGoalsBinding
    private val viewModel by viewModel<SeasonViewModel> { parametersOf() }
    private val goalsAdapter by inject<GoalsAdapter>()
    private val itemDecoration by inject<TableItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoalsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadData()
        setRecyclerView()
        observeData()

        binding.swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.th_secondary_blue)
            setOnRefreshListener { loadData() }
        }

        return binding.root
    }

    private fun loadData() = viewModel.getTopScorers()

    private fun observeData() {
        viewModel.topScorersLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    goalsAdapter.differ.submitList(result.data)
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Result.Error -> {
                    result.message?.let { error -> showSnackbar(binding.root, error) }
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Result.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = goalsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(itemDecoration)
        }
    }
}