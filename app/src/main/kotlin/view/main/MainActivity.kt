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

package view.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import base.view.BaseActivity
import com.pickth.onlymusicplayer.R
import extensions.convertDpToPixel
import kotlinx.android.synthetic.main.activity_main.*
import util.MusicDividerItemDecoration
import view.main.adapter.MusicListAdapter

/**
 * Created by yonghoon on 2017-08-23
 */

class MainActivity: BaseActivity(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mMusicAdapter: MusicListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMusicAdapter = MusicListAdapter()

        mPresenter = MainPresenter().apply {
            attachView(this@MainActivity)
            setMusicListAdapterView(mMusicAdapter)
            setMusicListAdapterModel(mMusicAdapter)
            getMusicFileList()
        }
    }

    override fun start() {
        rv_main_music_list.run {
            adapter = mMusicAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MusicDividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL, convertDpToPixel(10), true))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
        // 변경 전의 데이터 저장 후

        // setContentView

        // 데이터 바인딩
    }
}