package by.aderman.tottenhamhotspurfc.ui.fragments.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.adapters.team.TeamAdapter
import by.aderman.tottenhamhotspurfc.databinding.FragmentTeamBinding
import by.aderman.tottenhamhotspurfc.ui.fragments.news.NewsFragmentDirections
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.team.TeamViewModel
import com.google.android.material.snackbar.Snackbar

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private lateinit var viewModel: TeamViewModel
    private val teamAdapter by lazy { TeamAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setRecyclerView()
        observeData()

        teamAdapter.setOnItemClickListener {
            val action =
                TeamFragmentDirections.actionTeamFragmentToPlayerFragment(it)
            Navigation.findNavController(binding.root).navigate(action)
        }

        return binding.root
    }

    private fun observeData() {
        viewModel.teamLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    viewModel.changeResponseReceivedStatus(true)
                    teamAdapter.differ.submitList(it.data?.response?.get(0)?.players)
                }
                is Resource.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    it.message?.let { error -> showSnackbar(error) }
                }
                is Resource.Loading -> viewModel.changeResponseReceivedStatus(false)
            }
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = teamAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showSnackbar(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
}