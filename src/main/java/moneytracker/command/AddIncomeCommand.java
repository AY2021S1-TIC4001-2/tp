package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class AddIncomeCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>AddIncomeCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public AddIncomeCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoneyTrackerException {

    }
}