package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentLatestNewsBinding
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.presentation.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.news.NewsViewModel
import by.aderman.tottenhamhotspurfc.utils.MarginItemDecoration
import by.aderman.tottenhamhotspurfc.utils.OnBottomScrollListener
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LatestNewsFragment : Fragment() {

    private lateinit var binding: FragmentLatestNewsBinding
    private val viewModel by viewModel<NewsViewModel> { parametersOf() }
    private val newsAdapter by inject<NewsAdapter>()
    private val itemDecoration by inject<MarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLatestNewsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getNews()
        setRecyclerView()
        setItemDecoration()
        observeData()

        newsAdapter.setOnItemClickListener {
            val action =
                NewsFragmentDirections.actionNewsFragmentToArticleFragment(it)
            findNavController(binding.root).navigate(action)
        }

        return binding.root
    }

    private fun observeData() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Success -> {
                    viewModel.changeResponseReceivedStatus(true)
                    isLoading = false
                    newsAdapter.differ.submitList(it.data)
                }
                is Result.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    isLoading = false
                    it.message?.let { error -> showSnackbar(binding.root, error) }
                }
                is Result.Loading -> {
                    viewModel.changeResponseReceivedStatus(false)
                    isLoading = true
                }
            }
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(scrollListener)
        }
    }

    private fun setItemDecoration() {
        itemDecoration.margin = resources.getDimensionPixelSize(
            R.dimen.fragment_news_recycler_margin
        )
        binding.recyclerView.addItemDecoration(itemDecoration)
    }

// далее идет пагинация + часть в классе onBottomScrollListener

    var isLoading = false
    var isScrolling = false

    private val scrollListener = object : OnBottomScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onBottomReached() {
            if (!isLoading) {
                viewModel.getNews()
                isScrolling = false
            }
        }
    }
}