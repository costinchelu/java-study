package sec8d_linkedlists.challengepersonal;

// The program will have an Album class containing a list of songs.
// The albums will be stored in an ArrayList

import java.util.ArrayList;

public class Album {
    private String albumTitle;
    private ArrayList<Song> album;

    public String getAlbumTitle() { return albumTitle; }

    public ArrayList<Song> getAlbum() { return album; }

    public Album(String albumTitle) {
        this.albumTitle = albumTitle;
        this.album = new ArrayList<Song>();
    }
}
