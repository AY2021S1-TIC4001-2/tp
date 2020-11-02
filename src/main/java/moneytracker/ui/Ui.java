package moneytracker.ui;

import moneytracker.summary.Budget;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Category;
import moneytracker.transaction.CategoryList;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * Contains methods that interacts with the users such as obtaining commands
 * from user input and displaying messages to the users.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________________";
    public static final String INDENT = "   ";

    /**
     * Gets the input stream from the user.
     *
     * @return Input stream from the user.
     */
    public String readUserCommand() {
        Scanner in = new Scanner(System.in);
        System.out.print("You:  ");
        return in.nextLine().trim();
    }

    public void printLogo() {
        String logo = " __  __                          _______             _             " + System.lineSeparator()
                + "|  \\/  |                        |__   __|           | |            " + System.lineSeparator()
                + "| \\  / | ___  _ __   ___ _   _     | |_ __ __ _  ___| | _____ _ __ " + System.lineSeparator()
                + "| |\\/| |/ _ \\| '_ \\ / _ \\ | | |    | | '__/ _` |/ __| |/ / _ \\ '__|" + System.lineSeparator()
                + "| |  | | (_) | | | |  __/ |_| |    | | | | (_| | (__|   <  __/ |   " + System.lineSeparator()
                + "|_|  |_|\\___/|_| |_|\\___|\\__, |    |_|_|  \\__,_|\\___|_|\\_\\___|_|   " + System.lineSeparator()
                + "                          __/ |                                    " + System.lineSeparator()
                + "                         |___/                                     " + System.lineSeparator();
        System.out.println(logo);
    }
    
    public void printWelcome() {
        System.out.println("Hello! What can I do for you? Enter the command \"help\" to access the user guide.");
        printLine();
    }

    public void printSummary(double exp, double inc) {
        System.out.println(
                "Your total income for " + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                + " " + LocalDate.now().getYear() + ": $" + String.format("%.2f", inc)
                + "\nYour total expense for " + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                + " " + LocalDate.now().getYear() + ": $" + String.format("%.2f", exp));
    }

    public void printAddTransaction(TransactionList transactions) throws MoneyTrackerException {
        Transaction transactionToPrint = transactions.getTransaction(transactions.getSize() - 1);
        if (transactionToPrint instanceof Income) {
            System.out.println("Got it! I have added this income:");
        } else if (transactionToPrint instanceof Expense) {
            System.out.println("Got it! I have added this expense:");
        } else {
            throw new MoneyTrackerException("The transaction type is invalid");
        }
        printIndentation();
        System.out.println(transactionToPrint.toString());
        printIndentation();
        System.out.println("Now you have " + transactions.getSize() + " transactions in your list.");
        printLine();
    }


    public void printAddCategory(CategoryList categories) throws MoneyTrackerException {
        Category categoryToPrint = categories.getCategory(categories.getSize() - 1);
        if (categoryToPrint.getType().equals("INCOME")) {
            System.out.println("Got it! I have added this income category:");
        } else if (categoryToPrint.getType().equals("EXPENSE")) {
            System.out.println("Got it! I have added this expense category:");
        } else {
            throw new MoneyTrackerException("The category type is invalid");
        }
        printIndentation();
        System.out.println(categoryToPrint);
        printIndentation();
        System.out.println("Now you have " + categories.getSize() + " categories in your list.");
        printLine();
    }

    public void printRemoveCategory(int size, String description, String type) {
        System.out.println("Noted! I have removed this " + type + ": ");
        printIndentation();
        System.out.println(description);
        printIndentation();
        System.out.println("Now you have " + size + " categories in the list.");
        printLine();
    }

    public void printEditItem(String oldDescription, String newDescription, String type) {
        System.out.println("Noted! I have edited this " + type + ": ");
        printIndentation();
        System.out.println("From " + oldDescription + " to " + newDescription);
        printLine();
    }

    public void printError(String errorMessage) {
        System.out.println("OOPS!! " + errorMessage);
        printLine();
    }

    /**
     * Print the budget you have set.
     */

    public void printBudget(Budget budget) {
        double amount = budget.getAmount();
        String amountString = String.format("%.2f", amount);
        System.out.println("Got it! I have set your monthly budget to this amount:");
        printIndentation();
        System.out.println("$" + amountString);
        printLine();
    }

    /**
     * Print the percentage of the budget that has been exceeded.
     */

    public void printPctOfBudget(String str, double amount) {
        System.out.println("You have exceeded " + str + " of your budget of $" + String.format("%.2f", amount) + " for "
                + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                + " " + LocalDate.now().getYear() + ".");
    }

    public void printHelp() {
        System.out.println("Please refer this online user guide:");
        System.out.println("https://ay2021s1-tic4001-2.github.io/tp/UserGuide.html");
        printLine();
    }

    public void printGoodbye() {
        System.out.println("Bye! Hope to see you again soon.");
        printLine();
    }

    public void printClearConfirmation() {
        System.out.println("Are you sure you want to clear all data? Y / N");
    }

    public void printClearAllData(String confirmation) {
        if (confirmation.equals("Y")) {
            System.out.println("Noted! I have cleared all data.");
        } else if (confirmation.equals("N")) {
            System.out.println("Noted! Your data is untouched.");
        } else {
            System.out.println("Sorry, please enter \"Y\" or \"N\" only");
        }
        printLine();
    }

    public void printRemoveTransaction(int size, String description, String type) {
        System.out.println("Noted! I have removed this " + type + ": ");
        printIndentation();
        System.out.println(description);
        printIndentation();
        System.out.println("Now you have " + size + " transactions in the list.");
        printLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printIndentation() {
        System.out.print(INDENT);
    }

    /**
     * Executes the list transactions command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     */
    public void printListTransaction(TransactionList transactions) {
        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no transaction in your list.");
            return;
        } else {
            System.out.println("Here are your transactions:");
            for (int i = 0; i < transactions.getSize(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + transactions.getTransaction(i).toString());
            }
        }
        printLine();
    }

    /**
     * Executes the printMyFilteredTransactions comman.
     * To Print all the transactions in the filteredTransaction array list.
     *
     * @param filteredTransaction ArrayList of <code>Transaction</code> objects.
     */
    public void printMyFilteredTransactions(ArrayList<Transaction> filteredTransaction) {
        System.out.println("Here are your transactions:");
        for (int i = 0; i < filteredTransaction.size(); i++) {
            printIndentation();
            System.out.println((i + 1) + ". " + filteredTransaction.get(i));
        }
        printLine();
    }



    /**
     * Executes the Filtered Categories command.
     *
     * @param categories List of <code>categories</code> objects.
     */
    private void printFilteredCategories(CategoryList categories) {
        ArrayList<Integer> searchResultIndexes = categories.getSearchResultIndexes();
        {
            for (int i = 0; i < searchResultIndexes.size(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + categories.getCategory(searchResultIndexes.get(i)).toString());
            }
        }
        printLine();
    }

    /**
     * Executes the list category command.
     *
     * @param categories List of <code>categories</code> objects.
     */
    public void printListCategory(CategoryList categories) {
        if (categories.getSize() == 0) {
            System.out.println("Sorry, there is no category in your list.");
        } else {
            System.out.println("Here are your categories:");
            for (int i = 0; i < categories.getSize(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + categories.getCategory(i).toString());
            }
        }
        printLine();
    }

    /**
     * Executes the list category command to display expense categoty only.
     *
     * @param categories List of <code>categories</code> objects.
     */
    public void printListCategoryExpenseOnly(CategoryList categories) {
        categories.setIsInitialized(true);
        categories.clearSearchResultIndexes();

        if (categories.getSize() == 0) {
            System.out.println("Sorry, there is no category in your list.");
        } else {
            System.out.println("Here are your expenses categories:");
            for (int i = 0; i < categories.getSize(); i++) {
                if (categories.getCategory(i).getType().equals("EXPENSE")) {
                    categories.addSearchResultIndex(i);
                }
            }
        }
        printFilteredCategories(categories);
    }

    /**
     * Executes the list category command to display income categoty only.
     *
     * @param categories List of <code>categories</code> objects.
     */
    public void printListCategoryIncomeOnly(CategoryList categories) {
        categories.setIsInitialized(true);
        categories.clearSearchResultIndexes();

        if (categories.getSize() == 0) {
            System.out.println("Sorry, there is no category in your list.");
        } else {
            System.out.println("Here are your income categories:");
            for (int i = 0; i < categories.getSize(); i++) {
                if (categories.getCategory(i).getType().equals("INCOME")) {
                    categories.addSearchResultIndex(i);
                }
            }
        }
        printFilteredCategories(categories);
    }

    /**
     * Gets transaction/category  Month report from user's full input string.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param date month of <code>Transaction</code> objects.
     */
    public void printReportByMonth(TransactionList transactions, String date) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        printLine();
        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no transactions in your list.");
        } else {
            System.out.println("Here is your transactions for " + date + " :");

            double totalIncome = Parser.getTotalIncome(transactions,date);
            System.out.printf("Total Income: $%.2f\n",(totalIncome));

            double totalExpense = Parser.getTotalExpense(transactions,date);
            System.out.printf("Total Expense: $%.2f\n",(totalExpense));

            double balance = totalIncome - totalExpense;
            System.out.printf("Balance: $%.2f\n",(balance));

            double average = totalExpense / Parser.getDaysOfMonth(date);
            System.out.println("\nThis month has " + Parser.getDaysOfMonth(date) + " days.");
            System.out.printf("Average Expense Per Day: $%.2f\n",(average));
            System.out.println("");

            String highestIncomeTrans = Parser.getHighestIncome(transactions,date);
            System.out.println("Highest Income transaction: \n" + "  " + highestIncomeTrans);
            System.out.println("");

            String highestExpTrans = Parser.getHighestExpense(transactions,date);
            System.out.println("Highest Expense transaction: \n" + "  " + highestExpTrans);
            System.out.println("");

            System.out.println("Income Category by Frequency:");
            Parser.getInCatFreq(transactions,date);

            System.out.println("\nExpense Category by Frequency:");
            Parser.getExpCatFreq(transactions,date);

            System.out.println("\nIncome Category by Amount:");
            Parser.getInCatAmount(transactions,date);

            System.out.println("\nExpense Category by Amount:");
            Parser.getExpCatAmount(transactions,date);

            printLine();
        }
    }

    /**
     * Gets transaction/category last 6 Month report from user's full input string.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param dates month list of <code>Transaction</code> objects.
     */
    public void printReport(TransactionList transactions, List<String> dates) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        printLine();
        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no transactions in your list.");
        } else {
            System.out.println("Here is your report:");

            var lastSixIncome = Parser.getSixMonIncome(transactions, dates);
            System.out.println("Income for last 6 months (Highest to lowest):");
            Parser.printInHelper(lastSixIncome);

            var lastSixExpense = Parser.getSixMonExpense(transactions, dates);
            System.out.println("\nIncome for last 6 months (Highest to lowest):");
            Parser.printExpHelper(lastSixExpense);

            printLine();
        }
    }
}