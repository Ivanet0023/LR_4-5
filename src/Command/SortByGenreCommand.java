package Command;

public class SortByGenreCommand implements Command {

    private MusicDisk disk;
    public SortByGenreCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        disk.sortByGenre();
    }

    @Override
    public String getCommandName() {
        return "Sort disk by genre";
    }
}