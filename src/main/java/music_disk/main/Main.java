package music_disk.main;

import music_disk.track.MusicDisk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Launching MusicDisk");

        try {
            MusicDisk myDisk = new MusicDisk("Album");
            Menu menu = new Menu(myDisk);
            menu.run();
        } catch (Exception e) {
            logger.fatal("Fatal error!", e);
        }

        logger.info("Program finished.");
    }
}