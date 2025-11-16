package Command;

public class Main {

    public static void main(String[] args) {

        MusicDisk myDisk = new MusicDisk("Test");


        // Invoker
        Menu menu = new Menu();

        // Concrete

        Command calcDuration = new CalculateDurationCommand(myDisk);
        Command sortByGenre = new SortByGenreCommand(myDisk);

        // AddTrackCommand, FindTracksCommand...)

        Command exit = new ExitCommand();

        // --- 4. Реєстрація Команд у Меню ---
        menu.addCommand(1, calcDuration);
        menu.addCommand(2, sortByGenre);
        menu.addCommand(0, exit); // 0 - зазвичай для виходу

        menu.run();
    }
}