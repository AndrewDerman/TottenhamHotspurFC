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
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureStatus
import by.aderman.tottenhamhotspurfc.notifications.AlarmScheduler
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.FixturesAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.LinearMarginItemDecoration
import by.aderman.tottenhamhotspurfc.utils.getCurrentDateForApiRequest
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FixturesFragment : Fragment() {

    private lateinit var binding: FragmentFixturesBinding
    private val viewModel by viewModel<FixturesViewModel> { parametersOf() }
    private val fixturesAdapter by inject<FixturesAdapter>()
    private val itemDecoration by inject<LinearMarginItemDecoration>()
    private val fixturesFromRemote = mutableListOf<Fixture>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFixturesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerView()
        loadData()
        observeData()

        fixturesAdapter.setOnItemClickListener { fixtureId ->
            findNavController().navigate(
                MatchesFragmentDirections.actionMatchesFragmentToFixtureInfoFragment(fixtureId)
            )
        }

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                fixturesFromRemote.clear()
                loadData()
            }
            setColorSchemeResources(R.color.th_secondary_blue)
        }

        return binding.root
    }

    private fun loadData() = viewModel.getFixtures(getCurrentDateForApiRequest())

    private fun observeData() {
        viewModel.fixturesLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    result.data?.let { fixturesList ->
                        fixturesFromRemote.addAll(fixturesList.filter { fixture ->
                            fixture.status.shortValue != FixtureStatus.FT.name
                                    && fixture.status.shortValue != FixtureStatus.AET.name
                                    && fixture.status.shortValue != FixtureStatus.PEN.name
                                    && fixture.status.shortValue != FixtureStatus.AWD.name
                                    && fixture.status.shortValue != FixtureStatus.WO.name
                        })
                    }
                    fixturesAdapter.differ.submitList(fixturesFromRemote)
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                    viewModel.getSavedFixtures()
                    observeSavedFixtures()
                }
                is Result.Error -> {
                    result.message?.let { error ->
                        parentFragment?.view?.let { view ->
                            showSnackbar(view, error)
                        }
                    }
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Result.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun observeSavedFixtures() {
        viewModel.savedFixturesLiveData.observe(viewLifecycleOwner, { savedFixtures ->
            if (savedFixtures.isNullOrEmpty()) {
                viewModel.saveFixtures(fixturesFromRemote.filter { fixture ->
                    fixture.status.shortValue == FixtureStatus.NS.name
                            || fixture.status.shortValue == FixtureStatus.PST.name
                })
                viewModel.getSavedFixtures()
            } else {
                for (savedFixture in savedFixtures) {
                    if (!savedFixture.hasAlarm) {
                        AlarmScheduler.scheduleAlarm(requireContext(), savedFixture)
                        savedFixture.hasAlarm = true
                        viewModel.updateFixture(savedFixture)
                    } else {
                        val fixtureFromRemote =
                            fixturesFromRemote.first { it.id == savedFixture.id }
                        if (savedFixture.timestamp != fixtureFromRemote.timestamp) {
                            savedFixture.timestamp = fixtureFromRemote.timestamp
                            AlarmScheduler.scheduleAlarm(requireContext(), savedFixture)
                            viewModel.updateFixture(savedFixture)
                        }
                    }
                }
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