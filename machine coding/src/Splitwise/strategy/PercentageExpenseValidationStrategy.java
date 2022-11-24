package Splitwise.strategy;

import Splitwise.constants.Constants;
import Splitwise.model.Expense;
import Splitwise.model.ExpenseType;
import Splitwise.model.split.PercentageSplit;
import Splitwise.model.split.Split;

public class PercentageExpenseValidationStrategy implements ExpenseValidationStrategy {

    @Override
    public boolean validate(Expense expense) {
        if (expense.getExpenseType() != ExpenseType.PERCENTAGE) {
            return false;
        }

        for (Split split : expense.getSplits()) {
            if (!(split instanceof PercentageSplit)) {
                return false;
            }
        }
        double totalPercentage = 0.0;
        for (Split split : expense.getSplits()) {
            PercentageSplit percentageSplit = (PercentageSplit) split;
            totalPercentage = totalPercentage + percentageSplit.getPercentage();
        }

        return totalPercentage == Constants.DEFAULT_TOTAL_PERCENTAGE;
    }
}