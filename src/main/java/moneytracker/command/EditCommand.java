package moneytracker.command;

import moneytracker.summary.Budget;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.ui.Ui;

import java.util.HashMap;

import static moneytracker.command.Utilities.getTransactionType;
import static moneytracker.command.Utilities.getTransaction;

/**
 * Contains the methods for user to edit a transaction.
 */
public class EditCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>EditCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public EditCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the edit transaction command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of <code>Category</code> objects.
     * @param budget <code>Budget</code> object.
     * @throws MoneyTrackerException If there is a processing error.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories, Budget budget) throws MoneyTrackerException {
        if (!(transactions.getIsInitialized())) {
            throw new MoneyTrackerException("Please run the list command first.");
        }
        HashMap<String, String> editParams = Parser.getEditTransactionParams(fullCommand);
        int index = getIndex(editParams);
        Transaction transactionToEdit = getTransaction(transactions, index);
        String currentDescription = transactionToEdit.toString();
        String type = getTransactionType(transactionToEdit).toLowerCase();
        updateTransaction(transactionToEdit, editParams, categories, type);
        String newDescription = transactionToEdit.toString();
        storage.saveTransactions(transactions);
        ui.printEditItem(currentDescription, newDescription, type);
        transactions.setIsInitialized(false);
    }

    private int getIndex(HashMap<String, String> editParams) throws MoneyTrackerException {
        String indexString = editParams.get("index");
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        return index;
    }

    private void updateTransaction(Transaction transactionToEdit, HashMap<String, String> editParams,
                                   CategoryList categories, String type) throws MoneyTrackerException {
        if (editParams.containsKey("category")) {
            String category = editParams.get("category");
            if (!(categories.checkIfCategoryExists(category, type))) {
                throw new MoneyTrackerException("This category does not exist.");
            }
            if (getTransactionType(transactionToEdit).equals("INCOME")) {
                ((Income) transactionToEdit).setIncomeCategory(editParams.get("category"));
            }
            if (getTransactionType(transactionToEdit).equals("EXPENSE")) {
                ((Expense) transactionToEdit).setExpenseCategory(editParams.get("category"));
            }
        }
        if (editParams.containsKey("amount")) {
            double amount;
            try {
                amount = Double.parseDouble(editParams.get("amount"));
            } catch (NumberFormatException e) {
                throw new MoneyTrackerException("Amount should be a number. E.g. 3000.00");
            }
            if (amount < 0) {
                throw new MoneyTrackerException("Amount should not be a negative number.");
            }
            transactionToEdit.setAmount(amount);
        }
        if (editParams.containsKey("date")) {
            transactionToEdit.setDate(editParams.get("date"));
        }
        if (editParams.containsKey("description")) {
            transactionToEdit.setDescription(editParams.get("description"));
        }
    }
}