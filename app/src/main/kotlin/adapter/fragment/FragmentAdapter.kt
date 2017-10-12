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
import android.support.v4.app.FragmentManager
import android.util.Log
import base.view.BaseFragment
import listener.OnFragmentChangeListener
import view.music.MusicFragment
import view.music.PlayerFragment

/**
 * Created by yonghoon on 2017-08-28
 */

class FragmentAdapter(val fragmentManager: FragmentManager) : FragmentAdapterModel, OnFragmentChangeListener {
    val TAG = "$javaClass.simpleName"

    var currentPosition = 0
    private var frameLayoutId: Int = 0
    private var mMusicFragment: BaseFragment? = null
    private var mPlayerFragment: BaseFragment? = null

    override fun initialPage(position: Int) {
        Log.d(TAG, "initialPage $position")

//        onChange(position)
        fragmentManager
                .beginTransaction()
                .add(frameLayoutId, getItem(position))
                .commit()
    }

    override fun setFrameLayout(id: Int) {
        frameLayoutId = id
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> {
            if(mMusicFragment == null) {
                Log.d(TAG, "MusicFragment()")
                mMusicFragment = MusicFragment().apply { setOnFragmentChangeListener(this@FragmentAdapter) }
            }
            mMusicFragment!!
        }
        else -> {
            if(mPlayerFragment == null) mPlayerFragment = PlayerFragment().apply { setOnFragmentChangeListener(this@FragmentAdapter) }
            mPlayerFragment!!
        }
    }

    override fun onChange(position: Int) {
        Log.d(TAG, "onChange position: $position")
        currentPosition = position

        if (position == 0) {
//            fragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.no_action, 0, R.anim.no_action, 0)
//                    .replace(frameLayoutId, getItem(position))
//                    .commit()

        } else {
            // animation 에서 enter 가 exit 아래에서 수행되는걸 유의

//            fragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.slide_in_top, 0, R.anim.slide_in_top, 0)
//                    .replace(frameLayoutId, getItem(position))
//                    .commit()
        }
    }

    override fun getCurrentPage(): Int {
        return 0
    }
}