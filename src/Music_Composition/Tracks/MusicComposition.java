package Music_Composition.Tracks;

public abstract class MusicComposition {

    protected String title;
    protected String artist;
    protected int durationInSeconds;
    protected String genre;

    public MusicComposition(String title, String artist, int durationInSeconds, String genre) {
        this.title = title;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return durationInSeconds;
    }

    public String getGenre() {
        return genre;
    }

    public abstract String getFullInfo();
}
