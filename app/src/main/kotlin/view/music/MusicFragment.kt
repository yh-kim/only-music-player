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

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.view.BaseFragment
import com.pickth.onlymusicplayer.R
import extensions.convertDpToPixel
import kotlinx.android.synthetic.main.fragment_music.view.*
import music.MusicManager
import util.MusicDividerItemDecoration
import view.music.list.adapter.MusicListAdapter

/**
 * Created by yonghoon on 2017-08-28
 */

class MusicFragment : BaseFragment(), MusicContract.View {

    private lateinit var rootView: View
    private lateinit var mPresenter: MusicContract.Presenter
    private lateinit var mMusicAdapter: MusicListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_music, container, false)

        mMusicAdapter = MusicListAdapter()

        mPresenter = MusicPresenter().apply {
            attachView(this@MusicFragment)
            setMusicListAdapterView(mMusicAdapter)
            setMusicListAdapterModel(mMusicAdapter)
            getMusicListFromServer()
            getMusicListFromStorage()
            bindMusicStatus()
        }

        Log.d(TAG, "onCreateView")
        return rootView
    }

    override fun start() {
        // initialize listener
        rootView.rv_main_music_list.run {
            adapter = mMusicAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MusicDividerItemDecoration(context, LinearLayoutManager.VERTICAL, context.convertDpToPixel(10), true))
        }

        rootView.tv_main_music_play.setOnClickListener {
            if(MusicManager.getMediaPlayer().isPlaying) {
                mPresenter.pauseMusic()
            } else {
                mPresenter.playMusic()
            }
        }

        rootView.tv_main_music_stop.setOnClickListener {
            mPresenter.stopMusic()
        }
    }

    override fun showIsPlayingView(playState: Int) {
        when(playState) {
            MusicPresenter.STATE_PALY -> {
                rootView.tv_main_music_play.text = context.getString(R.string.main_pause)
                rootView.tv_main_music_info.text = "${mPresenter.getCurrentMusic()?.name} 재생 중 입니다."
            }
            MusicPresenter.STATE_PAUSE -> {
                rootView.tv_main_music_play.text = context.getString(R.string.main_play)
                rootView.tv_main_music_info.text = "${mPresenter.getCurrentMusic()?.name} 일시 정지 중 입니다"
            }
            MusicPresenter.STATE_STOP -> {
                rootView.tv_main_music_play.text = context.getString(R.string.main_play)
                rootView.tv_main_music_info.text = "재생중인 음악이 없습니다"
            }
        }
    }
}