package sec8d_linkedlists.challengepersonal;

// Create a program that implements a playlist for songs

// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.
// Once the songs have been added to the playlist, create a menu of options to:-
// Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
// List the songs in the playlist
// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).
// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.
// As an optional extra, provide an option to remove the current song from the playlist
// (hint: listiterator.remove() )

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Album album1 = new Album("Album 01");
        Album album2 = new Album("Album 02");

        Song song011 = new Song("Song 01 1", 2300);
        Song song012 = new Song("Song 01 2", 2340);
        Song song013 = new Song("Song 01 3", 2500);
        Song song021 = new Song("Song 02 1", 1800);
        Song song022 = new Song("Song 02 2", 1650);
        Song song023 = new Song("Song 02 3", 3100);
        Song song024 = new Song("Song 02 4", 2670);

        album1.getAlbum().add(song011);
        album1.getAlbum().add(song012);
        album1.getAlbum().add(song013);
        album2.getAlbum().add(song021);
        album2.getAlbum().add(song022);
        album2.getAlbum().add(song023);
        album2.getAlbum().add(song024);

        Playlist playlist1 = new Playlist("Playlist 1");

        playlist1.getPlaylist().add(album1.getAlbum().get(0));
        playlist1.getPlaylist().add(song012);
        playlist1.getPlaylist().add(song013);

        playlist1.getPlaylist().add(song021);
        playlist1.getPlaylist().add(song022);
        playlist1.getPlaylist().add(album2.getAlbum().get(2));
        playlist1.getPlaylist().add(album2.getAlbum().get(3));

        player(playlist1.getPlaylist());
    }

    private static void printOptions() {
        System.out.println("OPTIONS:\n");
        System.out.println("Press 0 = Stop playing\n" +
                "Press 1 = Play\n" +
                "Press 2 = Skip forward\n" +
                "Press 3 = Repeat song\n" +
                "Press 4 = Skip backwards\n" +
                "Press 5 = Remove from list\n" +
                "Press 6 = Print options");
    }

    private static void player(LinkedList<Song> playlist) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        ListIterator<Song> it = playlist.listIterator();

        printOptions();

        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Music stopped!");
                    quit = true;
                    break;
                case 1:
                    System.out.println("Play music!");
                    if(it.hasNext()){
                        System.out.println(">\n\t" + it.next().getTitle());
                        if(it.hasPrevious()) {
                            it.previous();
                        }
                    System.out.println("\t" + it.next().getDuration());
                    }
                    else {
                        System.out.println("No song in playlist");
                    }
                    break;
                case 5:
                    it.remove();
                case 2:
                    System.out.println(">");
                    if(it.hasNext()) {
                        System.out.println("\t" + it.next().getTitle());
                            if(it.hasPrevious()) {
                                it.previous();
                            }
                        System.out.println("\t" + it.next().getDuration());
                    }
                    else {
                        System.out.println("End of the playlist>>>>");
                    }
                    break;
                case 3:
                    System.out.println(">");
                    it.previous();
                    System.out.println("\t" + it.next().getTitle());
                    it.previous();
                    System.out.println("\t" + it.next().getDuration());
                    break;
                case 4:
                    System.out.println(">");
                    it.previous();
                    if(it.hasPrevious()) {
                        it.previous();
                        System.out.println("\t" + it.next().getTitle());
                        it.previous();
                        System.out.println("\t" + it.next().getDuration());
                    }
                    else {
                        System.out.println("<<<<Beginning of the playlist");
                    }
                    break;
            }
        }
    }

}
