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

package util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.onlymusicplayer.R
import extensions.convertDpToPixel

/**
 * Created by yonghoon on 2017-08-27
 */

/**
 * @param context
 * @param spacing dp
 * @param isDivider default value is true
 */
class MusicDividerItemDecoration(context: Context, val orientation: Int , var spacing: Int, val isDivider: Boolean): RecyclerView.ItemDecoration() {
    init {
        spacing = context.convertDpToPixel(spacing)
    }
    constructor(context: Context): this(context, 0)

    constructor(context: Context, spacing: Int): this(context, LinearLayoutManager.VERTICAL, spacing)

    constructor(context: Context, orientation: Int, spacing: Int) : this(context, orientation, spacing, true)

    private val mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if(orientation == LinearLayoutManager.VERTICAL) {
            outRect?.bottom = spacing
        } else {
            outRect?.right = spacing
        }
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {

        val childCount = parent!!.childCount
        for(i in 0..childCount-2) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            if(isDivider) {
                if(orientation == LinearLayoutManager.VERTICAL) {
                    val left = params.marginStart
                    val right: Int = child.right
                    // 아이템의 바텀 위치 + 바텀 마진
                    val top = child.bottom + params.bottomMargin + (spacing/2)
                    // top + 디바이더 고유 높이
                    val bottom = top + mDivider.intrinsicHeight

                    // 좌표값 설정
                    mDivider.setBounds(left, top, right, bottom)
                    mDivider.draw(c)
                } else {
                    val left = child.right + params.rightMargin + (spacing/2)
                    val right = left + mDivider.intrinsicWidth
                    val top = params.topMargin
                    val bottom = child.bottom

                    // 좌표값 설정
                    mDivider.setBounds(left, top, right, bottom)
                    mDivider.draw(c)
                }
            }


        }
    }
}