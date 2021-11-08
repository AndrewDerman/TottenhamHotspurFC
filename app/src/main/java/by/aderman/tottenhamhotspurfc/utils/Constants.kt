package by.aderman.tottenhamhotspurfc.utils

object Constants {

    //Common
    const val DATABASE_NAME = "Database"
    const val TIMEZONE_UTC = "UTC"
    const val OUTPUT_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss"
    const val API_TIMEOUT_IN_SECONDS = 30L
    const val FRAGMENTS_ID_KEY = "id"

    //News
    const val NEWS_BASE_URL = "https://newsapi.org/v2/"
    const val NEWS_API_QUERY_TITLE = "tottenham"
    const val NEWS_API_QUERY_LANGUAGE = "en"
    const val NEWS_API_QUERY_SORT_BY = "publishedAt"
    const val NEWS_API_QUERY_PAGE = 1
    const val ARTICLE_SHARE_TYPE = "text/html"
    const val ARTICLE_ENTITY_TABLE_NAME = "articles"
    const val ARTICLE_INPUT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    //Football
    const val FOOTBALL_BASE_URL = "https://v3.football.api-sports.io/"
    const val FOOTBALL_TEAM_ID = 47
    const val FOOTBALL_CURRENT_SEASON = 2021
    const val FOOTBALL_GOALKEEPER = "Goalkeeper"
    const val FOOTBALL_LEAGUE_ID = 39
    const val FOOTBALL_API_INPUT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX"
    const val FIXTURES_END_SEASON_DATE = "2022-12-31"
    const val FIXTURES_START_SEASON_DATE = "2021-01-01"
    const val FIXTURES_REQUEST_DATE_FORMAT = "yyyy-MM-dd"
    const val FOOTBALL_COUNTRY_NAME = "England"
    const val FOOTBALL_LEAGUE_NAME = "Premier League"

    //Notifications
    const val NOTIFICATIONS_CHANNEL_ID = "THFC_01"
    const val NOTIFICATIONS_CHANNEL_NAME = "Tottenham Hotspur notifications channel"
    const val NOTIFICATIONS_CHANNEL_DESC = "Matches starting notifications"
    const val NOTIFICATIONS_ID = 1882
    const val NOTIFICATIONS_REQUEST_CODE = 0
    const val NOTIFICATIONS_ACTION = "Notifications action"
    const val NOTIFICATIONS_TITLE = "Notification title"
    const val NOTIFICATIONS_MESSAGE = "Notification message"
}