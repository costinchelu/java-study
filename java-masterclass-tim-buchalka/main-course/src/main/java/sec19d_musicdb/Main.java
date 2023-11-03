package sec19d_musicdb;

import sec19d_musicdb.model.Artist;
import sec19d_musicdb.model.Datasource;
import sec19d_musicdb.model.SongArtist;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // datasource can come from different mediums (db, file, xml...)
        Datasource datasource = new Datasource();
        if(!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        // the method just provides a list of artists so it's flexible in the case it can accept data
        // from different sources. We can say that main does't know where data is coming from

        // column names version:
        List<Artist> artists = datasource.queryArtistColumnNames();

        // column index version (more efficient):
        List<Artist> artists1 = datasource.queryArtist();

        // ORDER BY version:
        List<Artist> artists2 = datasource.queryArtistOrder(Datasource.ORDER_BY_DESC);

        if(artists2 == null) {
            System.out.println("No artists!");
            return;
        }

//        for(Artist artist : artists2) {
//            System.out.println(artist.getId() + ". " + artist.getName());
//        }


        // query for a list (albums)
        List<String> albumsForArtists = datasource.queryAlbumForArtist(
                "Iron Maiden",
                Datasource.ORDER_BY_ASC);

        for (String album : albumsForArtists) {
            System.out.println(album);
        }

        // query for a list (custom object (SongArtist))
        List<SongArtist> songArtists = datasource.queryArtistsForSong("Heartless", Datasource.ORDER_BY_ASC);
        if(songArtists == null) {
            System.out.println("Couldn't find the artist for this song");
            return;
        }
        for (SongArtist artist : songArtists) {
            System.out.println("ARTIST: " + artist.getArtistName() + " ALBUM: " + artist.getAlbumName() + " TRACK: " +
                    artist.getTrack());
        }

        // query for metadata (column info)
        datasource.querySongsMetadata();

        // using function (count)
        System.out.println("Number of entries in the Songs table is "+
                datasource.getNoOfEntriesInATable(Datasource.TABLE_SONGS));

        // create the view
        datasource.createViewFromSongArtists();

        // using view for query
        songArtists = datasource.querySongInfoView("Go Your Own Way");
        if(songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist songArtist : songArtists) {
            System.out.println("QUERY FROM VIEW: " +
                    songArtist.getArtistName() +
                    " - ALBUM: " + songArtist.getAlbumName() +
                    " - TRACK NUMBER: " + songArtist.getTrack());
        }

        // demonstrating SQL Injection:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();

        songArtists = datasource.querySongInfoViewWithPrepStatement(title);
        if(songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist songArtist : songArtists) {
            System.out.println("QUERY FROM VIEW: " +
                    songArtist.getArtistName() +
                    " - ALBUM: " + songArtist.getAlbumName() +
                    " - TRACK NUMBER: " + songArtist.getTrack());
        }
        // What happens if we input: Go Your Own Way" or 1=1 or "
        // SQL injection attack - since 1=1 is always true, all the records in the view are returned

        // using prepared statements the query sent to the database is different:
        // with normal statement:
        // SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way" or 1=1 or ""
        // with prepared statement:
        // SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way or 1=1 or ""


        datasource.close();
    }
}
