package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.season

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.databinding.FragmentTableBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.TableAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.season.SeasonViewModel
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TableFragment : Fragment() {

    private lateinit var binding: FragmentTableBinding
    private val tableAdapter by inject<TableAdapter>()
    private val viewModel by viewModel<SeasonViewModel> { parametersOf() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getLeagueTable()
        setRecyclerView()
        observeData()

        return binding.root
    }

    private fun observeData() {
        viewModel.tableLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    tableAdapter.differ.submitList(it.data)
                    viewModel.changeResponseReceivedStatus(true)
                }
                is Result.Error -> {
                    it.message?.let { error -> showSnackbar(binding.root, error) }
                    viewModel.changeResponseReceivedStatus(true)
                }
                is Result.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tableAdapter
        }
    }
}