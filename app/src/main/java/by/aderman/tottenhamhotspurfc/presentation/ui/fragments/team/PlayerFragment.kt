package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.databinding.FragmentPlayerBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.team.TeamViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding
    private val viewModel by viewModel<TeamViewModel> { parametersOf() }
    private val args by navArgs<PlayerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        viewModel.getPlayerStatistic(args.currentPlayer.id)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        checkGoalkeeperStatus()
        observeData()
        return binding.root
    }

    private fun checkGoalkeeperStatus() {
        if (args.currentPlayer.position == Constants.FOOTBALL_GOALKEEPER) {
            viewModel.changeGoalkeeperStatus(true)
        } else viewModel.changeGoalkeeperStatus(false)
    }

    private fun observeData() {
        viewModel.playerLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    viewModel.changeResponseReceivedStatus(true)
                    it.data?.let { player -> bindData(player) }
                }
                is Result.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    it.message?.let { error -> showSnackbar(binding.root, error) }
                }
                is Result.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun bindData(playerWithStats: PlayerWithStats) {
        with(binding) {
            player = playerWithStats
            playerNumber.text = args.currentPlayer.number.toString()
        }
    }
}