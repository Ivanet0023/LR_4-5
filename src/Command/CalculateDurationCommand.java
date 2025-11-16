package Command;

public class CalculateDurationCommand implements Command {

    private MusicDisk disk;
    public CalculateDurationCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        int duration = disk.calculateTotalDuration();

    }

    @Override
    public String getCommandName() {
        return "Get total duration time";
    }
}