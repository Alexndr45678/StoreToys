import commands.*;
import log.Logger;
import log.SimpleLog;
import model.StoreToy;
import presenter.Presenter;
import storage.Storage;
import storage.StorageTxt;
import view.ConsoleUI;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public final static String DEFAULT_DATAPATH = "toys.txt";
    public final static String DEFAULT_LOGPATH = "result.log";
    public final static String CWD = "user.dir";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String dataPath = Paths.get(System.getProperty(CWD), DEFAULT_DATAPATH).toString();
        String logPath = Paths.get(System.getProperty(CWD), DEFAULT_LOGPATH).toString();
        Storage storage = new StorageTxt(dataPath);
        Logger logger = new SimpleLog(logPath);

        StoreToy toyStore = new StoreToy(storage, logger);
        Map<Integer, Command> commands = new HashMap<>();
        ConsoleUI view = new ConsoleUI(scan, commands);

        commands.put(1, new AddToy(toyStore, view));
        commands.put(2, new AddToys(toyStore, view));
        commands.put(3, new ChangeChanceToy(toyStore, view));
        commands.put(4, new RemoveToy(toyStore, view));
        commands.put(5, new RemoveToys(toyStore, view));
        commands.put(6, new ShowAllToys(toyStore, view));
        commands.put(7, new Prepare(toyStore, view));
        commands.put(8, new GetToy(toyStore, view));
        commands.put(9, new GetToys(toyStore, view));
        commands.put(0, new Exit(toyStore, view));

        Presenter presenter = new Presenter(view, commands);
        presenter.Run();
        scan.close();
    }
}
