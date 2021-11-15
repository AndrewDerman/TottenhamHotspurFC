package by.aderman.tottenhamhotspurfc.utils

object Constants {

    //Common
    const val DATABASE_NAME = "Database"
    const val TIMEZONE_UTC = "UTC"
    const val OUTPUT_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss"
    const val API_TIMEOUT_IN_SECONDS = 30L
    const val FIXTURES_ID_KEY = "fixture id"
    const val GRID_SPAN_COUNT = 2
    const val PREFERENCES_FILE_KEY = "by.aderman.shared_preferences"
    const val PLAYERS_UPDATE_TIME_KEY = "by.aderman.players_last_time_loading_from_remote"
    const val WEEK_TIME_IN_MILLIS = 604800000L
    const val PLAYERS_UPDATE_TIME_DEFAULT_VALUE = 0L

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
    const val FIXTURE_ENTITY_TABLE_NAME = "fixtures"
    const val RESULT_ENTITY_TABLE_NAME = "results"
    const val PLAYER_ENTITY_TABLE_NAME = "players"
    const val PLAYER_WITH_STATS_ENTITY_TABLE_NAME = "players_with_stats"

    //Notifications
    const val NOTIFICATIONS_CHANNEL_ID = "THFC_01"
    const val NOTIFICATIONS_CHANNEL_NAME = "Tottenham Hotspur notifications channel"
    const val NOTIFICATIONS_CHANNEL_DESC = "Matches starting notifications"
    const val NOTIFICATIONS_REQUEST_CODE = 0
    const val NOTIFICATIONS_ACTION = "Notifications action"
    const val NOTIFICATIONS_KEY_ID = "Fixture id"
    const val ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED"
}