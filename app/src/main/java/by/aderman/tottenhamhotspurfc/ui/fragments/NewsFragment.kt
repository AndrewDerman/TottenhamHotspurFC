package by.aderman.tottenhamhotspurfc.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.adapters.NewsAdapter
import by.aderman.tottenhamhotspurfc.databinding.FragmentNewsBinding
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setRecyclerView()
        observeNews()

        newsAdapter.setOnItemClickListener {
            val action =
                NewsFragmentDirections.actionNewsFragmentToArticleFragment(it)
            findNavController(binding.root).navigate(action)
        }

        return binding.root
    }

    private fun observeNews() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    viewModel.changeResponseReceivedStatus(true)
                    isLoading = false
                    it.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles?.toList())
                        // для пагинации
                        val totalPages =
                            newsResponse.totalResults?.div(Constants.NEWS_API_QUERY_PAGE_SIZE)
                                ?.plus(1)
                        isLastPage = viewModel.newsPage == totalPages
                    }
                }
                is Resource.Error -> {
                    viewModel.changeResponseReceivedStatus(true)
                    isLoading = false
                    it.message?.let { error ->
                        {
                            showSnackbar(error)
                            Log.d(Constants.LOADING_NEWS_ERROR, error)
                        }
                    }
                }
                is Resource.Loading -> {
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

    private fun showSnackbar(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()

    // далее идет пагинация + класс onBottomScrollListener

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : OnBottomScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onBottomReached() {
            if (!isLoading && !isLastPage) {
                viewModel.getAllNews()
                isScrolling = false
            }
        }
    }
}