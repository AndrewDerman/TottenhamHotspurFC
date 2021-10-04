package by.aderman.tottenhamhotspurfc.ui.fragments.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentPlayerBinding
import by.aderman.tottenhamhotspurfc.models.player.PlayerResponse
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.player.PlayerViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding
    private lateinit var viewModel: PlayerViewModel
    private val args by navArgs<PlayerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        args.currentPlayer.id?.let { viewModel.getPlayerStatistics(it) }
        checkGoalkeeperStatus()
        observeData()
        return binding.root
    }

    private fun checkGoalkeeperStatus() {
        if (args.currentPlayer.position == Constants.FOOTBALL_GOALKEEPER) {
            viewModel.changeGoalkeeperStatus(true)
        } else viewModel.changeGoalkeeperStatus((false))
    }

    private fun observeData() {
        viewModel.playerLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    viewModel.changeResponseReceivedStatus(true)
                    bindData(it)
                }
                is Resource.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    it.message?.let { error -> showSnackbar(error) }
                }
                is Resource.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun bindData(response: Resource<PlayerResponse>) {
        with(binding) {
            player = response.data?.response?.get(0)?.player
            playerNumber.text = args.currentPlayer.number.toString()
            Glide.with(root).load(args.currentPlayer.photo).into(playerPhoto)
            stats = response.data?.response?.get(0)?.statistics?.get(0)
        }
    }

    private fun convertData(value: Int?): Int {
        return value ?: 0
    }

    private fun showSnackbar(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
}