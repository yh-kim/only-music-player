/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package music

import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log

/**
 * Created by yonghoon on 2017-08-29
 */

object MusicManager {
    private val mPlayer = MediaPlayer()
    private val mRetriever = MediaMetadataRetriever()
    private val TAG = "OMP__${javaClass.simpleName}"

    fun getMediaPlayer() = mPlayer

    fun playWithFilePath(path: String): MediaPlayer = mPlayer.apply {
        Log.d(TAG, "playWithFilePath path: $path")
        setDataSource(path)
        prepare()
        start()
    }

    fun playWithUrl(context: Context, url: String): MediaPlayer = mPlayer.apply {
        Log.d(TAG, "playWithUrl url: $url")

        val uri = Uri.parse(url)
//        mRetriever.setDataSource(context, uri)

        setDataSource(context, uri)
        prepare()
        start()
    }

    fun isPlaying() = mPlayer.isPlaying

    fun getMusicInfo() {
        val info = "title: ${mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)}" +
                "artist: ${mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)}" +
                "album: ${mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)}"

        Log.d(TAG, info)
//        mPlayer.trackInfo
//        mPlayer.isPlaying
    }
}