package moneytracker.summary;

import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

import java.time.LocalDate;

public class Summary {

    /**
     * Calculates the Summary for <code>income</code>.
     */

    public static double calIncSummary(TransactionList transactions) {
        double inc = 0;
        for (Transaction tran : transactions.getTransactions()) {
            if (LocalDate.now().getMonth() == tran.getLocalDate().getMonth()) {
                if (tran instanceof Income) {
                    inc += tran.getAmountNumber();
                }
            }
        }
        return inc;
    }

    /**
     * Calculates the Summary of <code>expense</code>.
     */

    public static double calExpSummary(TransactionList transactions) {
        double exp = 0;
        for (Transaction tran : transactions.getTransactions()) {
            if (LocalDate.now().getMonth() == tran.getLocalDate().getMonth()) {
                if (tran instanceof Expense) {
                    exp += tran.getAmountNumber();
                }
            }
        }
        return exp;
    }


}
