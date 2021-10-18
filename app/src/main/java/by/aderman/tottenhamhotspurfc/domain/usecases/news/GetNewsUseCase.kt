package by.aderman.tottenhamhotspurfc.domain.usecases.news

import by.aderman.tottenhamhotspurfc.domain.repositories.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(page: Int) = repository.getRemoteNews(page)
}