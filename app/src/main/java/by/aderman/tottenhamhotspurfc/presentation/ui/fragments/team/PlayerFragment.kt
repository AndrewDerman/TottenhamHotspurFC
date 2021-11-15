package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentPlayerBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.team.TeamViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding
    private val viewModel by viewModel<TeamViewModel> { parametersOf() }
    private val args by navArgs<PlayerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadData()
        checkGoalkeeperStatus()
        observeData()

        with(binding) {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setTitleTextAppearance(requireContext(), R.style.ToolbarTextStyle)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }
            swipeRefreshLayout.apply {
                setOnRefreshListener { loadData() }
                setColorSchemeResources(R.color.th_secondary_blue)
            }
        }

        return binding.root
    }

    private fun loadData() = viewModel.getPlayerStatistic(args.currentPlayer.id)

    private fun checkGoalkeeperStatus() {
        if (args.currentPlayer.position == Constants.FOOTBALL_GOALKEEPER) {
            viewModel.changeGoalkeeperStatus(true)
        } else viewModel.changeGoalkeeperStatus(false)
    }

    private fun observeData() {
        viewModel.playerLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                    result.data?.let { player -> bindDataFromRemote(player) }
                }
                is Result.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    binding.swipeRefreshLayout.isRefreshing = false
                    result.message?.let { error -> showSnackbar(binding.root, error) }
                    bindDataFromLocal()
                }
                is Result.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun bindDataFromRemote(playerWithStats: PlayerWithStats) {
        with(binding) {
            player = playerWithStats
            playerNumber.text = args.currentPlayer.number.toString()
        }
    }

    private fun bindDataFromLocal() {
        with(binding) {
            val savedPlayer = args.currentPlayer
            playerFirstName.text = savedPlayer.name
            playerNumber.text = savedPlayer.number.toString()
            playerAge.text = savedPlayer.age.toString()
            playerPosition.text = savedPlayer.position
            Glide.with(root).load(savedPlayer.photo)
                .placeholder(R.drawable.no_image_placeholder)
                .error(R.drawable.no_image_placeholder)
                .into(playerPhoto)
        }
    }
}