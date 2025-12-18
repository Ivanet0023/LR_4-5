package music_disk.command;

import music_disk.track.MusicDisk;

public class CalculateDurationCommand implements Command {

    private MusicDisk disk;

    public CalculateDurationCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {

        int totalSeconds = disk.calculateTotalDuration();

        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        if (totalSeconds == 0) {
            System.out.println("Album is empty, or duration is 0.");
        } else {
            System.out.println(minutes + "min. " + seconds + "sec. (" + totalSeconds + " total sec.)");
        }
    }

    @Override
    public String getCommandName() {
        return "Calculate duration time";
    }
}