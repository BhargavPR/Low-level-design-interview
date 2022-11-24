package Splitwise.utils;

import Splitwise.model.Expense;
import Splitwise.model.ExpenseType;
import Splitwise.model.User;
import Splitwise.model.split.PercentageSplit;
import Splitwise.model.split.Split;

import java.util.List;

public class ExpenseUtils {

    public static Expense getExpense(User paidBy, double amount, ExpenseType expenseType, List<Split> splits) {
        if (expenseType.equals(ExpenseType.EQUAL)) {
            for (Split split : splits) {
                split.setAmount(amount / splits.size());
            }
            return new Expense("", amount, paidBy, splits, expenseType);
        } else if (expenseType.equals(ExpenseType.EXACT)) {
            return new Expense("", amount, paidBy, splits, expenseType);
        } else if (expenseType.equals(ExpenseType.PERCENTAGE)) {
            for (Split split : splits) {
                PercentageSplit percentageSplit = (PercentageSplit) split;
                split.setAmount((amount * percentageSplit.getPercentage()) / 100.0);
            }
            return new Expense("", amount, paidBy, splits, expenseType);
        }
        return null;
    }
}
