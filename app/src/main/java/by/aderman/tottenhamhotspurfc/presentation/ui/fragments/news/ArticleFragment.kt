package by.aderman.tottenhamhotspurfc.presentation.ui.fragments.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.FragmentArticleBinding
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.news.NewsViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.showSnackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val viewModel by viewModel<NewsViewModel> { parametersOf() }
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)

        with(binding) {

            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setTitleTextAppearance(requireContext(), R.style.ToolbarTextStyle)
                inflateMenu(R.menu.article_menu)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.save_article -> {
                            saveArticle()
                            true
                        }
                        R.id.share_article -> {
                            shareArticle()
                            true
                        }
                        else -> false
                    }
                }
            }

            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(args.currentArticle.url)
            }

            return binding.root
        }
    }

    private fun saveArticle() {
        viewModel.saveArticle(args.currentArticle)
        showSnackbar(binding.root, getString(R.string.success_article_save))
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
}
