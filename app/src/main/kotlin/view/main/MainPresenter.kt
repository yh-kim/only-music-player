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

import base.mvp.BaseView
import view.main.adapter.Music
import view.main.adapter.MusicListContract
import java.io.File

/**
 * Created by yonghoon on 2017-08-27
 */

class MainPresenter: MainContract.Presenter {

    private lateinit var mView: MainContract.View
    private lateinit var mMusicView: MusicListContract.View
    private lateinit var mMusicModel: MusicListContract.Model

    override fun attachView(view: BaseView<*>) {
        mView = view as MainContract.View
        mView.start()
    }

    override fun setMusicListAdapterView(view: MusicListContract.View) {
        mMusicView = view
    }

    override fun setMusicListAdapterModel(model: MusicListContract.Model) {
        mMusicModel = model
    }

    override fun getMusicFileList(): List<File> {
        val files = File("/storage/emulated/0/Download/")
                .listFiles()
                .filter {
                    it.name.substring(it.name.length - 3) == "mp3"
                }

        for(file in files) {
            mMusicModel.add(Music(file.name))
        }

        return files
    }

    override fun playMusic() {
    }

    override fun pauseMusic() {
    }

    override fun stopMusic() {
    }

    override fun getMusicStatus() {
    }
}