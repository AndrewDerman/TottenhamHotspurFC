package by.aderman.tottenhamhotspurfc.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.databinding.FragmentArticleBinding
import by.aderman.tottenhamhotspurfc.util.Constants
import by.aderman.tottenhamhotspurfc.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var viewModel: NewsViewModel
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        binding.webView.apply {
            webViewClient = WebViewClient()
            args.currentArticle.url?.let {
                loadUrl(it)
            }
        }

        binding.bSaveArticle.setOnClickListener {
            viewModel.saveArticle(args.currentArticle)
            showSnackbar()
        }

        binding.bShareArticle.setOnClickListener {
            shareArticle()
        }

        return binding.root
    }

    private fun shareArticle() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, args.currentArticle.url)
            type = Constants.ARTICLE_SHARE_TYPE
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun showSnackbar() =
        Snackbar.make(binding.root, Constants.SAVE_ARTICLE_SUCCESS_MESSAGE, Snackbar.LENGTH_SHORT)
            .show()
}