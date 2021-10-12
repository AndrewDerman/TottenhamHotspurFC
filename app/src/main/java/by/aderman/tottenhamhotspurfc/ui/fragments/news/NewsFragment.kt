package by.aderman.tottenhamhotspurfc.ui.fragments.news

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
import by.aderman.tottenhamhotspurfc.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.databinding.FragmentNewsBinding
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.util.MarginItemDecoration
import by.aderman.tottenhamhotspurfc.util.Resource
import by.aderman.tottenhamhotspurfc.viewmodel.news.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModel<NewsViewModel> { parametersOf() }
    private val newsAdapter by inject<NewsAdapter>()
    private val itemDecoration by inject<MarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setRecyclerView()
        setItemDecoration()
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
                    it.message?.let { error -> showSnackbar(error) }
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

    private fun setItemDecoration() {
        itemDecoration.margin = resources.getDimensionPixelSize(
            R.dimen.fragment_news_recycler_margin
        )
        binding.recyclerView.addItemDecoration(itemDecoration)
    }

    private fun showSnackbar(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()

    // далее идет пагинация + часть в классе onBottomScrollListener

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