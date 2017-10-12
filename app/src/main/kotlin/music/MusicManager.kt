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
import view.music.MusicPresenter
import view.music.list.adapter.Music

/**
 * Created by yonghoon on 2017-08-29
 */

object MusicManager {
    private val mPlayer = MediaPlayer()
    private val mRetriever = MediaMetadataRetriever()
    private val TAG = "${javaClass.simpleName}"
    private var mCurrentMusic: Music?= null

    fun getMediaPlayer() = mPlayer

    fun playWithFilePath(music: Music) {
        mPlayer.run {
            Log.d(TAG, "playWithFilePath path: $music.path")
            setDataSource(music.path)
            prepare()
            start()
            mCurrentMusic = music
        }
    }

    fun playWithUrl(context: Context, music: Music) {
        val uri = Uri.parse(music.url)
        mPlayer.run {
            Log.d(TAG, "playWithUrl url: $music.url")
            setDataSource(context, uri)
            prepare()
            start()
            mCurrentMusic = music
        }
    }

    fun pauseMusic() {
        if(mPlayer.isPlaying) {
            mPlayer.run {
                Log.d(TAG, "pauseMusic")
                pause()
            }
        }
    }

    fun stopMusic() {
        mPlayer.run {
            Log.d(TAG, "stopMusic")
            stop()
            reset()
            mCurrentMusic = null
        }
    }

    fun isPlaying() = mPlayer.isPlaying

    fun getMusicInfo() {
        val info = "title: ${mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)}" +
                "artist: ${mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)}" +
                "album: ${mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)}"

        Log.d(TAG, info)
    }

    fun getCurrentMusic(): Music? = mCurrentMusic
}