package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.databinding.FragmentFixtureInfoBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FixtureInfoFragment : Fragment() {

    private lateinit var binding: FragmentFixtureInfoBinding
    private val viewModel by viewModel<FixturesViewModel> { parametersOf() }
    private val args by navArgs<FixtureInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFixtureInfoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadData()
        observeData()

        return binding.root
    }


    private fun loadData() {
        viewModel.getFixtureInfo(args.currentFixtureId)
    }

    private fun observeData() {
        viewModel.fixtureInfoLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    binding.fixture = it.data
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
}