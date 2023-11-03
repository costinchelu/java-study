package sec9b_innerclass.challenge;

//code from LinkedList Challenge_myVersion

import java.util.*;

public class Main {

    //List is an interface so this also works: private static List<Album> albums = new ArrayList<>();
    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {

        //creating an album and adding its contents to albums arrayList
        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        //album object new reference(java collects garbage from the first iteration)
        //also adds new album to arrayList
        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        //creates a linkedList as a playlist and add songs from both albums
        // songs are added using title of song or track number
        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Speed king", playList);  // Does not exist
        albums.get(0).addToPlayList(9, playList);
        albums.get(1).addToPlayList(8, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(10, playList);  // There is no track 10
        albums.get(1).addToPlayList(24, playList);  // There is no track 24

        play(playList);
    }

    private static void play(LinkedList<Song> playlist) {
        Scanner scanner = new Scanner(System.in);
        boolean  quit = false;              //used for menu reentry
        boolean forward = true;             //used for direction of playback

        //using listIterator instead of simple iterator, permits traversing list both ways
        ListIterator<Song> listIterator = playlist.listIterator();

        if(playlist.size() == 0) {
            System.out.println("No songs in playlist");
            return;

            //we have to return null or else program will continue
            //list being empty we can't continue with the while loop
        }
        else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }


        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {

                    //quit
                case 0:
                    System.out.println("Stop playing.");
                    quit = true;
                    break;

                    //play next
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }

                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    }
                    else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;

                    //play previous
                case 2:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    }
                    else {
                        System.out.println("We have reached the start of the playlist");
                        forward = true;
                    }
                    break;

                    //replay
                case 3:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        }
                        else {
                            System.out.println("We have reached the start of the playlist");
                        }
                    }
                    else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        }
                        else {
                            System.out.println("We have reached the end of the playlist");
                        }
                    }
                    break;

                    //list songs
                case 4:
                    printList(playlist);
                    break;

                    //list menu
                case 5:
                    printMenu();
                    break;

                    //delete song
                case 6:
                    if(playlist.size() > 0) {
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        }
                        else if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions.\n" +
                "6 - delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playlist) {
        //normal iterator (we need to go forward (oneway) through the list
        Iterator<Song> it = playlist.iterator();
        System.out.println("================================");
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("================================");
    }
}

