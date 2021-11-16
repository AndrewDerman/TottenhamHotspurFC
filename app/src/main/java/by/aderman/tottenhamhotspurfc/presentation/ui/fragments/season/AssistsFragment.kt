package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.season

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentAssistsBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.AssistsAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.season.SeasonViewModel
import by.aderman.tottenhamhotspurfc.utils.TableItemDecoration
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AssistsFragment : Fragment() {

    private lateinit var binding: FragmentAssistsBinding
    private val viewModel by viewModel<SeasonViewModel> { parametersOf() }
    private val assistsAdapter by inject<AssistsAdapter>()
    private val itemDecoration by inject<TableItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssistsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadData()
        observeData()
        setRecyclerView()

        binding.swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.th_secondary_blue)
            setOnRefreshListener { loadData() }
        }

        return binding.root
    }

    private fun loadData() = viewModel.getTopAssists()

    private fun observeData() {
        viewModel.topAssistantsLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    assistsAdapter.differ.submitList(result.data)
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
            adapter = assistsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(itemDecoration)
        }
    }
}