package Music_Composition.Main;

import Music_Composition.Tracks.MusicDisk;

public class Main {

    public static void main(String[] args) {
        MusicDisk myDisk = new MusicDisk("Album");
        Menu menu = new Menu(myDisk);
        menu.run();
    }
}