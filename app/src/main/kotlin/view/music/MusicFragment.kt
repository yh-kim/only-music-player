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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.view.BaseFragment
import com.pickth.onlymusicplayer.R
import extensions.convertDpToPixel
import kotlinx.android.synthetic.main.fragment_music_list.view.*
import util.MusicDividerItemDecoration
import view.music.list.adapter.MusicListAdapter

/**
 * Created by yonghoon on 2017-08-28
 */

class MusicFragment : BaseFragment(), MusicContract.View {

    private lateinit var rootView: View
    private lateinit var mPresenter: MusicContract.Presenter
    private lateinit var mMusicAdapter: MusicListAdapter

    companion object {
        private val mInstance = MusicFragment()
        fun getInstance(): MusicFragment = mInstance
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_music_list, container, false)

        mMusicAdapter = MusicListAdapter()

        mPresenter = MusicPresenter().apply {
            attachView(this@MusicFragment)
            setMusicListAdapterView(mMusicAdapter)
            setMusicListAdapterModel(mMusicAdapter)
            getMusicFileList()
        }

        return rootView
    }

    override fun start() {
        rootView.rv_main_music_list.run {
            adapter = mMusicAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MusicDividerItemDecoration(context, LinearLayoutManager.VERTICAL, context.convertDpToPixel(10), true))
        }
    }
}