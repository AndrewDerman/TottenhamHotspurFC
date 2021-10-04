package by.aderman.tottenhamhotspurfc.util

object Constants {

    //Common
    const val NO_INTERNET_ERROR_MESSAGE = "No internet connection"

    //News
    const val NEWS_API_KEY = "c2b94316ad7749c1adb0dee2d00d0a71"
    const val NEWS_BASE_URL = "https://newsapi.org/v2/"
    const val NEWS_API_QUERY_TITLE = "tottenham"
    const val NEWS_API_QUERY_LANGUAGE = "en"
    const val NEWS_API_QUERY_SORT_BY = "publishedAt"
    const val NEWS_API_QUERY_PAGE = 1
    const val NEWS_API_QUERY_PAGE_SIZE = 20
    const val SAVE_ARTICLE_SUCCESS_MESSAGE = "Article saved successfully."
    const val DELETE_ARTICLE_SUCCESS_MESSAGE = "Article deleted successfully."
    const val LOADING_NEWS_ERROR = "LOADING NEWS ERROR: "
    const val ARTICLE_SHARE_TYPE = "text/html"

    //Football
    const val FOOTBALL_API_KEY = "067c1b5763139aa44e0a4e7b6670c880"
    const val FOOTBALL_BASE_URL = "https://v3.football.api-sports.io/"
    const val FOOTBALL_TEAM_ID = 47
    const val FOOTBALL_CURRENT_SEASON = 2021
    const val FOOTBALL_GOALKEEPER = "Goalkeeper"
}