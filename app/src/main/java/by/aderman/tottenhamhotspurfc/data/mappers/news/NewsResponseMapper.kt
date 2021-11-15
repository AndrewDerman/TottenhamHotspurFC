package by.aderman.tottenhamhotspurfc.data.mappers.news

import by.aderman.tottenhamhotspurfc.data.dto.news.NewsResponse
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.domain.models.news.Source
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.toLocalTime

class NewsResponseMapper {

    fun toArticleList(response: NewsResponse): List<Article>? {
        return response.articles?.map {
            Article(
                author = it.author.orEmpty(),
                content = it.content.orEmpty(),
                description = it.description.orEmpty(),
                publishedAt = toLocalTime(
                    it.publishedAt,
                    Constants.ARTICLE_INPUT_TIME_FORMAT
                ).orEmpty(),
                source = Source(it.source?.id.orEmpty(), it.source?.name.orEmpty()),
                title = it.title.orEmpty(),
                url = it.url.orEmpty(),
                urlToImage = it.urlToImage.orEmpty()
            )
        }
    }
}