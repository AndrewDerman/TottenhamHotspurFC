package by.aderman.tottenhamhotspurfc.ui.fragments.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.adapters.team.TeamAdapter
import by.aderman.tottenhamhotspurfc.databinding.FragmentTeamBinding
import by.aderman.tottenhamhotspurfc.util.MarginItemDecoration
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.util.showSnackbar
import by.aderman.tottenhamhotspurfc.viewmodel.team.TeamViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding
    private val viewModel by viewModel<TeamViewModel> { parametersOf() }
    private val teamAdapter by inject<TeamAdapter>()
    private val itemDecoration by inject<MarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setRecyclerView()
        setItemDecoration()
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
                    teamAdapter.differ.submitList(it.data?.response?.get(0)?.players?.filter { player ->
                        player.number != null
                    })
                }
                is Resource.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    it.message?.let { error -> showSnackbar(binding.root, error) }
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

    private fun setItemDecoration() {
        itemDecoration.margin = resources.getDimensionPixelSize(
            R.dimen.fragment_team_recycler_margin
        )
        binding.recyclerView.addItemDecoration(itemDecoration)
    }
}