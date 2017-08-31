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

/**
 * Created by yonghoon on 2017-08-27
 */

/**
 * @param type 0 is get file in storage, 1 is get file in server
 */
data class Music (var name: String, var type: Int, var path: String= "", var url: String = "") {
    companion object {
        val MUSIC_IN_STORAGE = 0
        val MUSIC_IN_SERVER = 1
    }
}