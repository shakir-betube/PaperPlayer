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

package xyz.michaelobi.paperplayer.data;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import xyz.michaelobi.paperplayer.data.library.LibraryManager;
import xyz.michaelobi.paperplayer.data.model.Album;
import xyz.michaelobi.paperplayer.data.model.Song;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * PaperPlayer Michael Obi 25 10 2016 3:50 AM
 */
public class MusicRepositoryTest {

    private final String albumName = "Revenge";
    private final String albumArtist = "Dreamville";
    private final String albumArtPath = "file://hello.jpg";
    private final String songTitle = "Folgers Crystals";

    @Mock
    private
    LibraryManager libraryManager;
    private MusicRepositoryInterface musicRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        musicRepository = new MusicRepository(libraryManager);

    }

    @Test
    public void getAllSongs_listsSongs_makesCorrectLibraryCalls() {
        when(libraryManager.fetchAllSongs()).thenReturn(Observable.just(songList()));

        TestSubscriber<List<Song>> subscriber = new TestSubscriber<>();
        musicRepository.getAllSongs().subscribe(subscriber);

        List<Song> songs = subscriber.getOnNextEvents().get(0);
        Assert.assertEquals(songTitle, songs.get(0).getTitle());
        Assert.assertEquals(albumArtist, songs.get(0).getArtist());
        Assert.assertEquals(albumName, songs.get(0).getAlbum());

        verify(libraryManager).fetchAllSongs();

    }

    @Test
    public void getAllAlbums_listsAlbums_returnsCorrectData() {
        when(libraryManager.fetchAllAlbums()).thenReturn(Observable.just(albumList()));

        TestSubscriber<List<Album>> subscriber = new TestSubscriber<>();
        musicRepository.getAllAlbums().subscribe(subscriber);

        List<Album> albums = subscriber.getOnNextEvents().get(0);
        Assert.assertEquals(albumName, albums.get(0).getTitle());

        verify(libraryManager).fetchAllAlbums();

    }

    private List<Song> songList() {
        Song song = new Song(1, songTitle, albumName, albumArtist, "2016", "", 1);
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        return songs;
    }

    private List<Album> albumList() {

        Album song = new Album(1, albumName, albumArtist, false, albumArtPath, 3);

        List<Album> albums = new ArrayList<>();
        albums.add(song);

        return albums;
    }

}