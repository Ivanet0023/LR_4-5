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

    public int getDuration() {
        return this.durationInSeconds;
    }

    public String getGenre() {
        return this.genre;
    }

    public abstract String getFullInfo();
}
