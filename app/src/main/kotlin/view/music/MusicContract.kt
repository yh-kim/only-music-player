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

import android.content.Context
import base.mvp.BasePresenter
import base.mvp.BaseView
import view.music.list.adapter.Music
import view.music.list.adapter.MusicListContract
import java.io.File

/**
 * Created by yonghoon on 2017-08-28
 */

interface MusicContract {
    interface View: BaseView<Presenter> {
        fun getContext(): Context
        fun showIsPlayingView(playState: Int)
    }

    interface Presenter: BasePresenter {
        fun setMusicListAdapterView(view: MusicListContract.View)
        fun setMusicListAdapterModel(model: MusicListContract.Model)
        fun getMusicListFromServer()
        fun getMusicListFromStorage(): List<File>
        fun playMusic()
        fun playMusic(music: Music)
        fun pauseMusic()
        fun stopMusic()
        fun getMusicStatus()
        fun getCurrentMusic(): Music?
    }
}