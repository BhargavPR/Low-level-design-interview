package Splitwise.strategy;

public class Context {

    public ExpenseValidationStrategy getExpenseValidationStrategy(String type) {
        switch (type) {
            case ExpenseValidationStrategy.EQUAL:
                return new EqualExpenseValidationStrategy();
            case ExpenseValidationStrategy.EXACT:
                return new ExactExpenseValidationStrategy();
            case ExpenseValidationStrategy.PERCENTAGE:
                return new PercentageExpenseValidationStrategy();
        }
        return null;
    }
}
