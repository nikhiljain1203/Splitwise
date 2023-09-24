package com.scaler.splitwise.strategies;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Expense> settleUp(List<Expense> expenses) {
        Map<User, Integer> extraAmount = new HashMap<>();
        List<Expense> transactions = new ArrayList<>();
        // Map to store extra amount to be paid to each use

        /*
        A PaidBy 1000
        B Had to Pay 500

        E1
        E2
        E2

        Extra Money
        A = 1000
        B = -500
         */

        // Extra MONEY MAP Creation
        for (Expense expense: expenses) {
            for (User user: expense.getPaidBy().keySet()) {
                if (!extraMoney.containsKey(user)) {
                    extraMoney.put(user, 0);
                }
                extraMoney.put(user, extraMoney.get(user) + expense.getPaidBy().get(user));
            }
            for (User user: expense.getHadToPay().keySet()) {
                if (!extraMoney.containsKey(user)) {
                    extraMoney.put(user, 0);
                }
                extraMoney.put(user, extraMoney.get(user) - expense.getHadToPay().get(user));
            }
        }
        /*
        A = 100
        B = -10
        C = -90

        A = 100


       B = -10
       C = -90
         */

        Queue<Record> negativeQueue = new PriorityQueue<>();
        // This queue will contain the amount had to pay by the users.

        Queue<Record> positiveQueue = new PriorityQueue<>();
        // This queue will contain the amount paid extra by the users.

        // For loop to fill the queue based on the extra amount map.
        for (User user: extraMoney.keySet()) {
            if (extraMoney.get(user) > 0) {
                positiveQueue.add(new Record(user, extraMoney.get(user)));
            } else {
                negativeQueue.add(new Record(user, extraMoney.get(user)));
            }
        }

        // create transaction list.
        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()) {
            Record firstNegative = negativeQueue.remove(); // user paid lesser.
            Record firstPostive = positiveQueue.remove(); // user paid extra.

            if (firstPostive.amount > Math.abs(firstNegative.amount)) {
                //1000  > -500
                // B -> A 500
                transactions.add(
                        new Expense(firstNegative.user.toDto(), firstPostive.user.toDto(), Math.abs(firstNegative.pendingAmount))
                );
                // A -> 1000-500 = 500
                positiveQueue.add(new Record(firstPostive.user, firstPostive.pendingAmount - Math.abs(firstNegative.pendingAmount)));
            } else {
                // 500 > -1000
                transactions.add(
                        new Expense(firstNegative.user.toDto(), firstPostive.user.toDto(), firstPostive.pendingAmount)
                );
                negativeQueue.add(new Record(firstNegative.user, firstNegative.pendingAmount + firstPostive.pendingAmount));
            }
        }
        return transactions;
    }
}
