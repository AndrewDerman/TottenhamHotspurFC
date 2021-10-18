package by.aderman.tottenhamhotspurfc.domain.usecases.news

import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.domain.repositories.NewsRepository

class DeleteArticleUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(article: Article) = repository.deleteArticle(article)
}