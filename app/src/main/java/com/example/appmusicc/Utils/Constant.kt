package com.example.appmusicc.Utils

object Constant {
    const val NO_EXIT = -1
    const val CONNECT_TIME_OUT = 10000
    const val READ_TIME_OUT = 10000
    const val DEFAULT_TOP_ALBUM_COUNT = 3
    const val DEFAULT_FIRST_LOAD_COUNT = 10
    const val LOAD_MORE_COUNT = 5
    const val FIRST_ITEM_INDEX = 0
    const val FIRST_POSITION_INDEX = 1
    const val ALBUM_ID = "ALBUM_ID"
    const val DOWNLOAD_URL = "DOWNLOAD_URL"
    const val NAME_MUSIC = "NAME_MUSIC"

    fun createUrlString(
        baseUrl: String,
        key: String,
        router: String,
        params: Array<out String>
    ): String {
        return "$baseUrl$router?apikey=$key&${params.joinToString(prefix = "&", separator = "&")}"
    }
}
