package by.aderman.tottenhamhotspurfc.data.mappers.news

import by.aderman.tottenhamhotspurfc.data.dto.news.ArticleLocal
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.domain.models.news.Source

class ArticleLocalMapper {

    fun toArticleLocal(article: Article): ArticleLocal {
        return ArticleLocal(
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            sourceId = article.source.id,
            sourceName = article.source.name,
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage
        )
    }

    fun toArticle(articleLocal: ArticleLocal): Article {
        return Article(
            author = articleLocal.author,
            content = articleLocal.content,
            publishedAt = articleLocal.publishedAt,
            description = articleLocal.description,
            source = Source(articleLocal.sourceId, articleLocal.sourceName),
            title = articleLocal.title,
            url = articleLocal.url,
            urlToImage = articleLocal.urlToImage
        )
    }
}