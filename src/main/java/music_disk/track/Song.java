package music_disk.track;

public class Song extends MusicComposition {

    private String lyricsAuthor;
    private String instrumentalProducer;

    public Song(String title, String artist, int durationInSeconds, String genre,
                String lyricsAuthor, String instrumentalProducer) {

        super(title, artist, durationInSeconds, genre);
        this.lyricsAuthor = lyricsAuthor;
        this.instrumentalProducer = instrumentalProducer;
    }

    @Override
    public String getFullInfo() {
        return "Song{" +
                "lyricsAuthor='" + lyricsAuthor + '\'' +
                ", instrumentalProducer='" + instrumentalProducer + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                ", genre='" + genre + '\'' +
                '}';
    }
}
