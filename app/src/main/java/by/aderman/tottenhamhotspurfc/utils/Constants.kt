package by.aderman.tottenhamhotspurfc.utils

object Constants {

    //Common
    const val DATABASE_NAME = "Database"

    //News
    const val NEWS_BASE_URL = "https://newsapi.org/v2/"
    const val NEWS_API_QUERY_TITLE = "tottenham"
    const val NEWS_API_QUERY_LANGUAGE = "en"
    const val NEWS_API_QUERY_SORT_BY = "publishedAt"
    const val NEWS_API_QUERY_PAGE = 1
    const val ARTICLE_SHARE_TYPE = "text/html"
    const val ARTICLE_ENTITY_TABLE_NAME = "articles"
    const val ARTICLE_TIMEZONE_UTC = "UTC"
    const val ARTICLE_INPUT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val ARTICLE_OUTPUT_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss"

    //Football
    const val FOOTBALL_BASE_URL = "https://v3.football.api-sports.io/"
    const val FOOTBALL_TEAM_ID = 47
    const val FOOTBALL_CURRENT_SEASON = 2021
    const val FOOTBALL_GOALKEEPER = "Goalkeeper"
}