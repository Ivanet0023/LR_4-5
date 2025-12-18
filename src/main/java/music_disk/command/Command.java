package music_disk.command;

public interface Command {

    void execute();
    String getCommandName();
}