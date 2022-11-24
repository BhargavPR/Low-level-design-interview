package Splitwise.service;

import Splitwise.repsitory.ExpenseRepository;
import Splitwise.repsitory.UserRepository;
import Splitwise.model.Expense;
import Splitwise.model.ExpenseType;
import Splitwise.model.User;
import Splitwise.model.split.Split;
import Splitwise.utils.ExpenseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseService {

    private UserRepository userRepository;
    private ExpenseRepository expenseRepository;

    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public void insertExpense(String paidBy, double amount, ExpenseType expenseType, List<Split> splits) {
        User paidByUser = userRepository.getUserByName(paidBy);
        if (paidByUser == null) {
            throw new RuntimeException("User do not found");
        }
        expenseRepository.insertExpense(paidByUser, amount, expenseType, splits);
    }

    public void showBalance(String userName) {
        HashMap<String, Double> balanceSheet = expenseRepository.getBalanceSheet(userName);

        if (balanceSheet == null || balanceSheet.size() == 0) {
            System.out.println("No balances");
            return;
        }

        for (Map.Entry<String, Double> balance : balanceSheet.entrySet()) {
            System.out.println(userName + " " + balance.getKey() + " => " + balance.getValue());
        }
    }

    public void showBalances() {
        if (expenseRepository.getBalanceMap().isEmpty()) {
            System.out.println("showBalances: No balances");
            return;
        }
        
        List<User> users = userRepository.getUsers();
        for (User user : users) {
            showBalance(user.getName());
            System.out.println();
        }
    }
}
