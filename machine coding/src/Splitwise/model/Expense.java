package Splitwise.model;

import Splitwise.model.split.Split;

import java.util.List;
import java.util.UUID;

public class Expense {

    private String id;
    private String title;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseType expenseType;

    public Expense(String id, String title, double amount, User paidBy, List<Split> splits, ExpenseType expenseType) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseType = expenseType;
    }

    public Expense(String title, double amount, User paidBy, List<Split> splits, ExpenseType expenseType) {
        this(UUID.randomUUID().toString(), title, amount, paidBy, splits, expenseType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
