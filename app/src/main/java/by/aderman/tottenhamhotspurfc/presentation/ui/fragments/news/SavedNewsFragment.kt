package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.news

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentSavedNewsBinding
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.presentation.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.presentation.ui.activities.MainActivity
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.news.NewsViewModel
import by.aderman.tottenhamhotspurfc.utils.MarginItemDecoration
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SavedNewsFragment : Fragment() {

    private lateinit var binding: FragmentSavedNewsBinding
    private val viewModel by viewModel<NewsViewModel> { parametersOf() }
    private val newsAdapter by inject<NewsAdapter>()
    private val itemDecoration by inject<MarginItemDecoration>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        viewModel.getBookmarks()
        setRecyclerView()
        setItemDecoration()
        observeData()
        setDeletionWithSwipe()

        newsAdapter.setOnItemClickListener {
            val action =
                NewsFragmentDirections.actionNewsFragmentToArticleFragment(it)
            Navigation.findNavController(binding.root).navigate(action)
        }

        return binding.root
    }

    private fun observeData() {
        viewModel.getBookmarks().observe(viewLifecycleOwner, {
            newsAdapter.differ.submitList(it)
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setItemDecoration() {
        itemDecoration.margin = resources.getDimensionPixelSize(
            R.dimen.fragment_news_recycler_margin
        )
        binding.recyclerView.addItemDecoration(itemDecoration)
    }

    private fun setDeletionWithSwipe() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val currentArticle = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(currentArticle)
                showSnackbar(currentArticle)
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    private fun showSnackbar(currentArticle: Article) {
        Snackbar.make(
            binding.root,
            getString(R.string.success_article_delete),
            Snackbar.LENGTH_LONG
        )
            .apply {
                setAction(getString(R.string.operation_undo)) {
                    viewModel.saveArticle(currentArticle)
                }
            }
            .setAnchorView(R.id.bottom_nav_view)
            .show()
    }
}