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

package view.music.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.onlymusicplayer.R
import listener.OnMusicClickListener

/**
 * Created by yonghoon on 2017-08-27
 */

class MusicListAdapter: RecyclerView.Adapter<MusicListViewHolder>(), MusicListContract.View, MusicListContract.Model {

    private lateinit var mMusicClickListener: OnMusicClickListener
    private var mItems = ArrayList<Music>()

    override fun onBindViewHolder(holder: MusicListViewHolder, position: Int) {
        holder.onBind(mItems[position], position)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_music, parent, false)

        return MusicListViewHolder(itemView, mMusicClickListener)
    }

    override fun setMusicClickListener(listener: OnMusicClickListener) {
        mMusicClickListener = listener
    }

    override fun getItem(position: Int): Music = mItems[position]

    override fun add(item: Music) {
        mItems.add(item)
        notifyItemInserted(itemCount - 1)
    }
}