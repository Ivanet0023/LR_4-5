package Tracks;

public class Instrumental extends MusicComposition {

    private String instrumentalProducer;

    public Instrumental(String title, String artist, int durationInSeconds, String genre,
                        String instrumentalProducer) {

        super(title, artist, durationInSeconds, genre);
        this.instrumentalProducer = instrumentalProducer;
    }

    @Override
    public String getFullInfo() {
        return "Song{" +
                ", instrumentalProducer='" + instrumentalProducer + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                ", genre='" + genre + '\'' +
                '}';
    }
}
