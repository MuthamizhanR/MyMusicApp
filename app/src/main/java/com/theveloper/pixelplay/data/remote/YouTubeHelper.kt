package com.theveloper.pixelplay.data.remote

import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.stream.StreamInfoItem

object YouTubeHelper {

    // 1. Search YouTube and return list of songs
    fun search(query: String): List<YouTubeSong> {
        try {
            val service = ServiceList.YouTube
            val searchExtractor = service.getSearchExtractor(query, listOf(), "")
            searchExtractor.fetchPage()

            return searchExtractor.initialPage.items
                .filterIsInstance<StreamInfoItem>()
                .map { item ->
                    YouTubeSong(
                        videoId = item.url.replace("https://www.youtube.com/watch?v=", ""),
                        title = item.name,
                        artist = item.uploaderName,
                        thumbnail = item.thumbnailUrl
                    )
                }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    // 2. Get the direct Audio URL
    fun getAudioUrl(videoId: String): String {
        try {
            val url = "https://www.youtube.com/watch?v=$videoId"
            val extractor = ServiceList.YouTube.getStreamExtractor(url)
            extractor.fetchPage()

            // Find best audio quality (m4a is best for Android)
            return extractor.audioStreams
                .sortedByDescending { it.bitrate }
                .firstOrNull()?.content ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}

data class YouTubeSong(
    val videoId: String,
    val title: String,
    val artist: String,
    val thumbnail: String
)