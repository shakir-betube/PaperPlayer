/*
 * MIT License
 *
 * Copyright (c) 2017 MIchael Obi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package xyz.michaelobi.paperplayer.data.model

import android.database.Cursor
import android.provider.MediaStore

/**
 * PaperPlayer
 * Michael Obi
 * 15 10 2016 3:23 PM
 */

class Song {

    var title: String? = null
    var album: String? = null
    var artist: String? = null
    var year: String? = null
    var songUri: String? = null
    var id: Long = 0
    var albumId: Long = 0

    constructor()

    constructor(id: Long, title: String, album: String, artist: String, year: String, songUri: String) {
        this.id = id
        this.title = title
        this.album = album
        this.artist = artist
        this.year = year
        this.songUri = songUri
    }

    companion object {
        fun from(cursor: Cursor): Song {
            val titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
            val idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
            val artistColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST)
            val pathColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
            val albumIdColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
            val albumColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)
            val song = Song(
                    cursor.getLong(idColumn),
                    cursor.getString(titleColumn),
                    cursor.getString(albumColumn),
                    cursor.getString(artistColumn),
                    "",
                    cursor.getString(pathColumn)
            )
            song.albumId = cursor.getLong(albumIdColumn)
            return song
        }
    }
}
