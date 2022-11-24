package Splitwise;

import Splitwise.action.Action;
import Splitwise.model.ExpenseType;
import Splitwise.model.User;
import Splitwise.model.split.EqualSplit;
import Splitwise.model.split.ExactSplit;
import Splitwise.model.split.PercentageSplit;
import Splitwise.model.split.Split;
import Splitwise.repsitory.ExpenseRepository;
import Splitwise.repsitory.UserRepository;
import Splitwise.service.ExpenseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplitwiseApplication {

    public static void main(String[] args) throws FileNotFoundException {
        UserRepository userRepository = UserRepository.getInstance();
        ExpenseRepository expenseRepository = ExpenseRepository.getInstance();

        ExpenseService expenseService = new ExpenseService(userRepository, expenseRepository);

        User user1 = new User("u1", "u1@gmail.com");
        User user2 = new User("u2", "u2@gmail.com");
        User user3 = new User("u3", "u3@gmail.com");
        User user4 = new User("u4", "u4@gmail.com");

        userRepository.insertUser(user1);
        userRepository.insertUser(user2);
        userRepository.insertUser(user3);
        userRepository.insertUser(user4);

        Scanner scanner = new Scanner(new File("/Users/20033132/machine coding/machine coding/src/Splitwise/input.txt"));

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            Action action = Action.valueOf(input[0]);
            switch (action) {
                case SHOW_TOTAL:
                    expenseService.showBalances();
                    break;
                case SHOW:
                    expenseService.showBalance(input[1]);
                    break;
                case EXPENSE:
                    handleAddExpenseRequest(input);
            }
        }
    }

    private static void handleAddExpenseRequest(String[] input) {
        UserRepository userRepository = UserRepository.getInstance();
        ExpenseRepository expenseRepository = ExpenseRepository.getInstance();

        ExpenseService expenseService = new ExpenseService(userRepository, expenseRepository);

        String paidByUserName = input[1];
        Double amount = Double.parseDouble(input[2]);
        ExpenseType expenseType = ExpenseType.valueOf(input[3]);
        int totalMember = Integer.parseInt(input[4]);

        List<String> userNames = new ArrayList<>();
        for (int i = 5; i < 5 + totalMember; i++) {
            userNames.add(input[i]);
        }

        List<Split> splits = new ArrayList<>();
        int index = 5 + totalMember;

        if (expenseType.equals(ExpenseType.EXACT)) {
            for (String userName : userNames) {
                User user = userRepository.getUserByName(userName);
                splits.add(new ExactSplit(user, Double.parseDouble(input[index])));
                index++;
            }

        } else if (expenseType.equals(ExpenseType.PERCENTAGE)) {
            for (String userName : userNames) {
                User user = userRepository.getUserByName(userName);
                splits.add(new PercentageSplit(user, Double.parseDouble(input[index])));
                index++;
            }
        } else {
            for (String userName : userNames) {
                User user = userRepository.getUserByName(userName);
                splits.add(new EqualSplit(user));
                index++;
            }
        }
        expenseService.insertExpense(paidByUserName, amount, expenseType, splits);
    }
}
