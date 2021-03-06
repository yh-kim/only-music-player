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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.view.BaseFragment
import com.pickth.onlymusicplayer.R
import kotlinx.android.synthetic.main.fragment_player.view.*

/**
 * Created by yonghoon on 2017-08-28
 */

class PlayerFragment : BaseFragment() {

    private lateinit var rootView: View

    companion object {
        val PLAYER_FRAGMENT_TAG = "PLAYER"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_player, container, false)

        rootView.tv_player_screen.setOnClickListener {
            fragmentChangeListener.onChange(0)
        }

        return rootView
    }


}