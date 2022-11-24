package Splitwise.strategy;

import Splitwise.model.Expense;
import Splitwise.model.ExpenseType;
import Splitwise.model.split.EqualSplit;
import Splitwise.model.split.Split;

public class EqualExpenseValidationStrategy implements ExpenseValidationStrategy {

    @Override
    public boolean validate(Expense expense) {
        if (expense.getExpenseType() != ExpenseType.EQUAL) {
            return false;
        }

        for (Split split : expense.getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        return false;
    }
}
