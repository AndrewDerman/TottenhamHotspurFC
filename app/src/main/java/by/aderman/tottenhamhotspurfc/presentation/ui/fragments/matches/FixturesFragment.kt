package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentFixturesBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureStatus
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.FixturesAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.MarginItemDecoration
import by.aderman.tottenhamhotspurfc.utils.getCurrentDateForApiRequest
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FixturesFragment : Fragment() {

    private lateinit var binding: FragmentFixturesBinding
    private val viewModel by viewModel<FixturesViewModel> { parametersOf() }
    private val fixturesAdapter by inject<FixturesAdapter>()
    private val itemDecoration by inject<MarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFixturesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerView()
        viewModel.getFixtures(getCurrentDateForApiRequest())
        observeData()

        fixturesAdapter.setOnItemClickListener {
            findNavController().navigate(
                MatchesFragmentDirections.actionMatchesFragmentToFixtureInfoFragment(it)
            )
        }

        return binding.root
    }

    private fun observeData() {
        viewModel.fixturesLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    fixturesAdapter.differ.submitList(it.data?.filter { fixture ->
                        fixture.status.shortValue != FixtureStatus.FT.name
                                && fixture.status.shortValue != FixtureStatus.AET.name
                                && fixture.status.shortValue != FixtureStatus.PEN.name
                                && fixture.status.shortValue != FixtureStatus.AWD.name
                                && fixture.status.shortValue != FixtureStatus.WO.name
                    })
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
            adapter = fixturesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(itemDecoration.also {
                it.margin = resources.getDimensionPixelSize(
                    R.dimen.fragment_news_recycler_margin
                )
            })
        }
    }
}