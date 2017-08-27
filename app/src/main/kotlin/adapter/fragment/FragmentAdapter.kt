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

package adapter.fragment

import android.support.v4.app.Fragment
import view.music.MusicFragment

/**
 * Created by yonghoon on 2017-08-28
 */

class FragmentAdapter(val fragmentManager: android.support.v4.app.FragmentManager): FragmentAdapterModel {

    private var frameLayoutId: Int = 0
    private var mMusicFragment: Fragment = MusicFragment.getInstance()

    override fun setFrameLayout(id: Int) {
        frameLayoutId = id
    }

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> {
            showMusicFragment()
            mMusicFragment
        }
        else -> {
            mMusicFragment
        }
    }

    override fun showMusicFragment() {
        if(fragmentManager != null) {
            fragmentManager.beginTransaction().run {
                setCustomAnimations(0,0)
                replace(frameLayoutId, mMusicFragment)
                        .commit()
            }
        }
    }

    override fun showPlayerFragment() {
    }

    override fun getCurrentPage(): Int {
        return 0
    }
}