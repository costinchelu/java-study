package sec8d_linkedlists.challengepersonal;

// Create a Song class having title and duration for a song.

public class Song {
    private String title;
    private int duration;

    public String getTitle() { return title; }
    public int getDuration() { return duration; }

    public Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
}
