package sec19e_transactions;

import sec19e_transactions.model.Artist;
import sec19e_transactions.model.Datasource;
import sec19e_transactions.model.SongArtist;


public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

//        datasource.insertSong("Touch of Grey", "Grateful Dead", "In the Dark", 1);
//        datasource.insertSong("Until My Last Breath", "Tarja Turunen", "What Lies Beneath", 2);


        datasource.close();
    }
}
