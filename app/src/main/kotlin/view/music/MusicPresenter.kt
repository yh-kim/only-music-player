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

package view.music

import android.util.Log
import base.mvp.BaseView
import listener.OnMusicClickListener
import music.MusicManager
import view.music.list.adapter.Music
import view.music.list.adapter.MusicListContract
import java.io.File

/**
 * Created by yonghoon on 2017-08-28
 */

class MusicPresenter : MusicContract.Presenter, OnMusicClickListener {

    private lateinit var mView: MusicContract.View
    private lateinit var mMusicView: MusicListContract.View
    private lateinit var mMusicModel: MusicListContract.Model
    val TAG = "$javaClass.simpleName"

    companion object {
        val STATE_PALY = 0
        val STATE_PAUSE = 1
        val STATE_STOP = 2
    }

    override fun attachView(view: BaseView<*>) {
        mView = view as MusicContract.View
        mView.start()
    }

    override fun setMusicListAdapterView(view: MusicListContract.View) {
        mMusicView = view
        mMusicView.setMusicClickListener(this)
    }

    override fun setMusicListAdapterModel(model: MusicListContract.Model) {
        mMusicModel = model
    }

    override fun getMusicListFromServer() {
        mMusicModel.add(Music("아이유 - 밤편지", 1, url = "https://s3.ap-northeast-2.amazonaws.com/comepenny/music/%EC%95%84%EC%9D%B4%EC%9C%A0+-+%EB%B0%A4%ED%8E%B8%EC%A7%80.mp3"))
    }

    override fun getMusicListFromStorage(): List<File> {
        val files = File("/storage/emulated/0/Download/")
                .listFiles()
                .filter {
                    it.name.substring(it.name.length - 3) == "mp3"
                }

        for(file in files) {
            mMusicModel.add(Music(file.name, 0, path = file.path))
        }

        return files
    }

    override fun onMusicClick(position: Int) {
        playMusic(mMusicModel.getItem(position))
    }

    override fun playMusic() {
        val mCurrentMusic = MusicManager.getCurrentMusic()
        if(mCurrentMusic != null) {
            playMusic(mCurrentMusic)
        } else {
            playMusic(mMusicModel.getItem(0))
        }
    }

    override fun playMusic(music: Music) {
        val mCurrentMusic = MusicManager.getCurrentMusic()
        val player = MusicManager.getMediaPlayer().apply {
            setOnCompletionListener {
                MusicManager.stopMusic()
                bindMusicStatus()
            }
        }

        Log.d(TAG, "playMusic")

        if(music != mCurrentMusic) {
            Log.d(TAG, "Playing music is not mCurrentMusic")
            MusicManager.stopMusic()

            if(music.type == Music.MUSIC_IN_STORAGE) {
                MusicManager.playWithFilePath(music)
            } else {
                MusicManager.playWithUrl(mView.getContext(), music)
            }

        } else {
            Log.d(TAG, "Playing music is mCurrentMusic")
        }

        // at pause
        if(!player.isPlaying) {
            player.run {
                start()
            }
        }
        bindMusicStatus()
    }

    override fun pauseMusic() {
        MusicManager.pauseMusic()
        bindMusicStatus()
    }

    override fun stopMusic() {
        MusicManager.stopMusic()
        bindMusicStatus()
    }

    override fun bindMusicStatus() {
        val mCurrentMusic = MusicManager.getCurrentMusic()
        if(mCurrentMusic == null) {
            mView.showIsPlayingView(STATE_STOP)
        } else {
            if(MusicManager.getMediaPlayer().isPlaying) {
                mView.showIsPlayingView(STATE_PALY)
            } else {
                mView.showIsPlayingView(STATE_PAUSE)
            }
        }
    }

    override fun getCurrentMusic(): Music? = MusicManager.getCurrentMusic()
}