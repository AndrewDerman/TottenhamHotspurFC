package by.aderman.tottenhamhotspurfc.data.mappers.news

import by.aderman.tottenhamhotspurfc.data.dto.news.NewsResponse
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.domain.models.news.Source
import by.aderman.tottenhamhotspurfc.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class NewsResponseMapper {

    fun toArticleList(response: NewsResponse): List<Article>? {
        return response.articles?.map {
            Article(
                author = it.author.orEmpty(),
                content = it.content.orEmpty(),
                description = it.description.orEmpty(),
                publishedAt = toLocalTime(it.publishedAt).orEmpty(),
                source = Source(it.source?.id.orEmpty(), it.source?.name.orEmpty()),
                title = it.title.orEmpty(),
                url = it.url.orEmpty(),
                urlToImage = it.urlToImage.orEmpty()
            )
        }
    }

    private fun toLocalTime(inputTime: String?): String? {
        val inputFormat =
            SimpleDateFormat(Constants.ARTICLE_INPUT_TIME_FORMAT, Locale.getDefault()).also {
                it.timeZone = TimeZone.getTimeZone(Constants.ARTICLE_TIMEZONE_UTC)
            }
        val outputFormat =
            SimpleDateFormat(Constants.ARTICLE_OUTPUT_TIME_FORMAT, Locale.getDefault()).also {
                it.timeZone = TimeZone.getDefault()
            }

        if (inputTime != null) {
            val date = inputFormat.parse(inputTime)
            date?.let {
                return outputFormat.format(it)
            }
        }
        return null
    }
}