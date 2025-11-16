package Tracks;

public class Vokal extends MusicComposition {

    private String lyricsAuthor;
    public Vokal(String title, String artist, int durationInSeconds, String genre,
                 String lyricsAuthor) {

        super(title, artist, durationInSeconds, genre);
        this.lyricsAuthor = lyricsAuthor;
    }

    @Override
    public String getFullInfo() {
        return "Vokal{" +
                "lyricsAuthor='" + lyricsAuthor + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                ", genre='" + genre + '\'' +
                '}';
    }
}