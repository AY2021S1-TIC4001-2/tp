package moneytracker.summary;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests for Summary class.
 */

public class SummaryTest {

    /**
     * Tests the Summary calculation of <code>Expense</code>.
     */

    @Test
    public void expenseSummaryTest() {

        ArrayList<Transaction> t = new ArrayList<>();

        try {
            Expense expense1 = new Expense(300, "dinner", "2020-10-10", "FOOD");
            t.add(expense1);

            Expense expense2 = new Expense(200.87, "snack", "2020-10-08", "FOOD");
            t.add(expense2);
            TransactionList transactions = new TransactionList(t);
            double exp = Summary.calExpSummary(transactions);
            assertEquals("Your total expense for Oct 2020: $500.87", "Your total expense for "
                    + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                    + " " + LocalDate.now().getYear() + ": $" + String.format("%.2f", exp));

        } catch (MoneyTrackerException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Tests the summary calculation of <code>Income</code>.
     */

    @Test
    public void incomeSummaryTest() {

        ArrayList<Transaction> i = new ArrayList<>();

        try {
            Income income1 = new Income(1000, "fulltime", "2020-10-10", "SALARY");
            i.add(income1);

            Income income2 = new Income(200.87, "parttime", "2020-10-08", "SALARY");
            i.add(income2);
            TransactionList transactions = new TransactionList(i);
            double inc = Summary.calIncSummary(transactions);
            assertEquals("Your total income for Oct 2020: $1200.87", "Your total income for "
                    + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                    + " " + LocalDate.now().getYear() + ": $" + String.format("%.2f", inc));

        } catch (MoneyTrackerException e) {
            System.out.print(e.getMessage());
        }

    }
}