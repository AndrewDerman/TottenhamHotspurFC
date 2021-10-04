package by.aderman.tottenhamhotspurfc.ui.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.databinding.FragmentSavedNewsBinding
import by.aderman.tottenhamhotspurfc.models.news.Article
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.viewmodel.news.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class SavedNewsFragment : Fragment() {

    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var viewModel: NewsViewModel
    private val newsAdapter by lazy { NewsAdapter() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setRecyclerView()
        observeData()
        setDeletionWithSwipe()

        newsAdapter.setOnItemClickListener {
            val action =
                    SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(it)
            Navigation.findNavController(binding.root).navigate(action)
        }

        return binding.root
    }

    private fun setDeletionWithSwipe() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = true

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

    private fun observeData() {
        viewModel.showSavedArticles().observe(viewLifecycleOwner, {
            newsAdapter.differ.submitList(it)
        })
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showSnackbar(currentArticle: Article) {
        Snackbar.make(binding.root, Constants.DELETE_ARTICLE_SUCCESS_MESSAGE, Snackbar.LENGTH_LONG).apply {
            setAction("Undo") {
                viewModel.saveArticle(currentArticle)
            }
        }.show()
    }
}