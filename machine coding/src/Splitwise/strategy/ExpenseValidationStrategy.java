package Splitwise.strategy;

import Splitwise.model.Expense;

public interface ExpenseValidationStrategy {

    public static final String EQUAL = "EQUAL";
    public static final String EXACT = "EXACT";
    public static final String PERCENTAGE = "PERCENTAGE";

    boolean validate(Expense expense);
}
