package by.aderman.tottenhamhotspurfc.domain.usecases.news

import by.aderman.tottenhamhotspurfc.domain.repositories.NewsRepository

class GetBookmarksUseCase(private val repository: NewsRepository) {
    operator fun invoke() = repository.getBookmarks()
}