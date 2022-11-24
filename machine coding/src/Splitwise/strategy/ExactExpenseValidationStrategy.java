package Splitwise.strategy;

import Splitwise.model.Expense;
import Splitwise.model.ExpenseType;
import Splitwise.model.split.ExactSplit;
import Splitwise.model.split.Split;

public class ExactExpenseValidationStrategy implements ExpenseValidationStrategy {

    @Override
    public boolean validate(Expense expense) {
        if (expense.getExpenseType() != ExpenseType.EXACT) {
            return false;
        }

        for (Split split : expense.getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }

        double totalAmount = expense.getAmount();
        double splitAmount = expense.getSplits().stream().mapToDouble(Split::getAmount).sum();

        return totalAmount == splitAmount;
    }
}