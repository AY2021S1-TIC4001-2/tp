package moneytracker.summary;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for Budget class.
 */

public class BudgetTest {

    /**
     * Tests the percentage calculation of <code>Budget</code>.
     */

    @Test
    public void budgetTest() {

        /**
         * Tests expense exceeded 50 percent.
         */
        double amount = 2000.00;
        Budget b = new Budget(amount);
        String str1 = b.calPercentage(1000.00);
        assertEquals("You have exceeded 50% of your budget of $2000.00 for Oct 2020.","You have exceeded "
                + str1 + " of your budget of $" + String.format("%.2f", amount) + " for "
                + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                + " " + LocalDate.now().getYear() + ".");


        /**
         * Tests expense exceeded 75 percent.
         */
        String str2 = b.calPercentage(1751.00);
        assertEquals("You have exceeded 75% of your budget of $2000.00 for Oct 2020.","You have exceeded "
                + str2 + " of your budget of $" + String.format("%.2f", amount) + " for "
                + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                + " " + LocalDate.now().getYear() + ".");


        /**
         * Tests the expense exceeded 100 percent.
         */
        String str3 = b.calPercentage(2500.10);
        assertEquals("You have exceeded 100% of your budget of $2000.00 for Oct 2020.","You have exceeded "
                + str3 + " of your budget of $" + String.format("%.2f", amount) + " for "
                + LocalDate.now().getMonth().getDisplayName(TextStyle.SHORT, Locale.US)
                + " " + LocalDate.now().getYear() + ".");

    }

}
