package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.aderman.tottenhamhotspurfc.databinding.FragmentEventsBinding
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.EventsAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel by lazy { requireParentFragment().getViewModel<FixturesViewModel>() }
    private val eventsAdapter by inject<EventsAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setRecyclerView()
        observeData()

        return binding.root
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, {
            val events = it.data?.events
            if (!events.isNullOrEmpty()) {
                viewModel.changeEventsStatus(true)
                eventsAdapter.differ.submitList(events.reversed())
            } else {
                viewModel.changeEventsStatus(false)
            }
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}