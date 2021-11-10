package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.team

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentTeamBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.adapters.team.TeamAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.team.TeamViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.GridMarginItemDecoration
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private val viewModel by viewModel<TeamViewModel> { parametersOf() }
    private val teamAdapter by inject<TeamAdapter>()
    private val itemDecoration by inject<GridMarginItemDecoration>()
    private val preferences by inject<SharedPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerView()
        loadData()
        observeSavedData()

        teamAdapter.setOnItemClickListener {
            findNavController().navigate(
                TeamFragmentDirections.actionTeamFragmentToPlayerFragment(it)
            )
        }

        binding.swipeRefreshLayout.setOnRefreshListener { loadData() }

        return binding.root
    }

    private fun loadData() {
        viewModel.getSavedTeamSquad()
    }

    private fun observeSavedData() {
        viewModel.savedTeamLiveData.observe(viewLifecycleOwner, { playersList ->
            val lastUpdateTime = preferences.getLong(
                Constants.PLAYERS_UPDATE_TIME_KEY,
                Constants.PLAYERS_UPDATE_TIME_DEFAULT_VALUE
            )
            val diffTimeInMillis = System.currentTimeMillis() - lastUpdateTime
            if (!playersList.isNullOrEmpty() && diffTimeInMillis <= Constants.WEEK_TIME_IN_MILLIS) {
                teamAdapter.differ.submitList(playersList)
                viewModel.changeResponseReceivedStatus(true)
                binding.swipeRefreshLayout.isRefreshing = false
            } else {
                viewModel.getRemoteTeamSquad()
                observeRemoteData()
            }
        })
    }

    private fun observeRemoteData() {
        viewModel.teamLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    val remotePlayers = it.data?.filter { player ->
                        player.number != null
                    }
                    teamAdapter.differ.submitList(remotePlayers?.sortedBy { player ->
                        player.number
                    })
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false

                    if (remotePlayers != null) {
                        for (player in remotePlayers) {
                            viewModel.savePlayer(player)
                        }
                    }

                    with(preferences.edit()) {
                        putLong(Constants.PLAYERS_UPDATE_TIME_KEY, System.currentTimeMillis())
                        apply()
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
            adapter = teamAdapter
            layoutManager = GridLayoutManager(requireContext(), Constants.GRID_SPAN_COUNT)
            addItemDecoration(itemDecoration.also {
                it.margin = resources.getDimensionPixelSize(
                    R.dimen.fragment_team_recycler_margin
                )
            })
        }
    }
}