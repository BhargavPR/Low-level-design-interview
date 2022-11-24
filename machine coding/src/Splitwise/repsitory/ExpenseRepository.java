package Splitwise.repsitory;

import Splitwise.model.Expense;
import Splitwise.model.ExpenseType;
import Splitwise.model.User;
import Splitwise.model.split.Split;
import Splitwise.utils.ExpenseUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();
    private final HashMap<String, HashMap<String, Double>> balanceMap = new HashMap<>();

    private static ExpenseRepository INSTANCE = null;

    public static ExpenseRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExpenseRepository();
        }
        return INSTANCE;
    }

    public void insertExpense(User paidByUser, double amount, ExpenseType expenseType, List<Split> splits) {
        Expense expense = ExpenseUtils.getExpense(paidByUser, amount, expenseType, splits);
        if (expense == null) {
            throw new RuntimeException("Invalid expense");
        }

        expenses.add(expense);
        for (Split split : splits) {
            User paidToUser = split.getUser();

            if (balanceMap.get(paidByUser.getName()) == null) {
                balanceMap.put(paidByUser.getName(), new HashMap<>());
            }

            if (balanceMap.get(paidToUser.getName()) == null) {
                balanceMap.put(paidToUser.getName(), new HashMap<>());
            }

            HashMap<String, Double> paidByBalanceMap = balanceMap.get(paidByUser.getName());
            HashMap<String, Double> paidToBalanceMap = balanceMap.get(paidToUser.getName());

            if (!paidByBalanceMap.containsKey(paidToUser.getName())) {
                paidByBalanceMap.put(paidToUser.getName(), 0.0);
            }
            paidByBalanceMap.put(paidToUser.getName(), paidByBalanceMap.get(paidToUser.getName()) + split.getAmount());

            if (!paidToBalanceMap.containsKey(paidByUser.getName())) {
                paidToBalanceMap.put(paidByUser.getName(), 0.0);
            }
            paidToBalanceMap.put(paidByUser.getName(), paidToBalanceMap.get(paidByUser.getName()) - split.getAmount());
        }
    }

    public HashMap<String, Double> getBalanceSheet(String userName) {
        return balanceMap.get(userName);
    }

    public HashMap<String, HashMap<String, Double>> getBalanceMap() {
        return balanceMap;
    }

}
