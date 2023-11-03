package sec19e_transactions.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    // connection string
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\GITHUB\\JAVA\\Udemy (Tim Buchalka)\\" + DB_NAME;

    // tables and column names and indexes
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    // ORDER BY options
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // INSERTS for transactions. In case a new song is from a new album, we will need to insert that album.
    // Also this is valid for a new artist.
    private static final String INSERT_SONGS =
            "INSERT INTO " + TABLE_SONGS +
                    '(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM + ") VALUES(?, ?, ?)";

    private static final String INSERT_ARTIST =
            "INSERT INTO " + TABLE_ARTISTS +
                    '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";

    private static final String INSERT_ALBUMS =
            "INSERT INTO " + TABLE_ALBUMS +
            '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";

    // we need to query the id of an album or artist. If we can't find it, then we have to insert it
    private static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    private static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    // connection
    private Connection conn;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement queryArtistForId;
    private PreparedStatement queryAlbumForId;


    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            queryAlbumForId = conn.prepareStatement(QUERY_ALBUM);
            queryArtistForId = conn.prepareStatement(QUERY_ARTIST);

            return true;
        } catch (SQLException e) {
            System.out.println("Cannot connect to the database " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            // closing the prepared statement
            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if(insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if(queryArtistForId != null) {
                queryArtistForId.close();
            }
            if(queryAlbumForId != null) {
                queryAlbumForId.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot close the connection " + e.getMessage());
            e.printStackTrace();
        }
    }


    private int insertArtist(String name) throws SQLException {
        // we try to find the artist
        queryArtistForId.setString(1, name);
        ResultSet results = queryArtistForId.executeQuery();
        // getting the id of the artist
        if(results.next()) {
            // in this case we find the id of the artist we queried
            return results.getInt(1);
        } else {
            // we can't find the artist by name so we have to insert one
            insertIntoArtists.setString(1, name);
            int affectedRows = insertIntoArtists.executeUpdate();   // this should be 1 (1 row inserted)
            if(affectedRows != 1) {
                // we expected that executeUpdate return 1 (row), but that didn't happen
                throw new SQLException("Could't insert artist");
            }
            // that's how we get the id of the newly inserted artist
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for the inserted artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbumForId.setString(1, name);
        ResultSet results = queryAlbumForId.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();
            if(affectedRows != 1) {
                throw new SQLException("Could't insert album");
            }
            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for the inserted album");
            }
        }
    }

    public void insertSong(String title, String artist, String album, int track) {
        try {
            // setting autocommit off for building a transaction
            conn.setAutoCommit(false);

            // getting albumId by verifying for existing entries
            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            // inserting values into the new song entry
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);
            // executing the insertion. If all goes well, we will commit the transaction
            int affectedRows = insertIntoSongs.executeUpdate();
            if(affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("Song insertion failed!");
            }

        } catch(Exception e) {
            // case something went wrong and we have to rollback
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Cannot rollback! " + e.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e3) {
                System.out.println("Error resetting autocommit! " + e3.getMessage());
            }
        }
    }
}
