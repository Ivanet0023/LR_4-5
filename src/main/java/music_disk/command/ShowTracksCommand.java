package music_disk.command;

import music_disk.track.MusicComposition;
import music_disk.track.MusicDisk;

import java.util.List;

public class ShowTracksCommand implements Command {
    private MusicDisk disk;

    public ShowTracksCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        List<MusicComposition> tracks = disk.getTracks();

        if (tracks.isEmpty()) {
            System.out.println("(Empty)");
        } else {
            for (int i = 0; i < tracks.size(); i++) {
                System.out.println((i + 1) + ". " + tracks.get(i).getFullInfo());
            }
        }
    }

    @Override
    public String getCommandName() {
        return "Check the album";
    }
}