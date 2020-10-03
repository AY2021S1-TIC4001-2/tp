package moneytracker;

import moneytracker.command.Command;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class MoneyTracker {
    private final Storage storage;
    private final Ui ui;
    private TransactionList tasks;

    /**
     * Initializes a <code>MoneyTracker</code> object.
     *
     * @param filePath Path of the text file used for storing app data.
     */
    public MoneyTracker(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TransactionList(storage.loadTransactions(filePath));
        } catch (MoneyTrackerException e) {
            ui.printError(e.getMessage());
            tasks = new TransactionList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MoneyTrackerException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     *  Main method of Money Tracker. This is the starting point of the app.
     *  @param args Command line arguments. Not used.
     */
    public static void main(String[] args) {
        new MoneyTracker("data/tasks.txt").run();
    }
}