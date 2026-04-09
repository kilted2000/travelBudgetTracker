package com.travelbudget.service;

import com.travelbudget.model.Category;
import com.travelbudget.model.Expense;
import com.travelbudget.model.Trip;

/**
 * Handles all budget calculations for a Trip.
 * Keeping math here (not in Trip or Main) makes it easy to test
 * and upgrade independently later.
 *
 * INTERMEDIATE upgrade ideas:
 *   - Add getSpendingByDay() using expense dates
 *   - Add getCategoryBudgetStatus() to check per-category limits
 *   - Add getDailyBurnRate() and projectedOverage()
 */
public class BudgetService {

    // -------------------------------------------------------------------------
    // Core Calculations
    // -------------------------------------------------------------------------

    /**
     * Returns the total amount spent across all expenses on a trip.
     */
    public double getTotalSpent(Trip trip) {
        double total = 0;
        for (Expense expense : trip.getExpenses()) {
            total += expense.getAmount();
        }
        return total;
    }

    /**
     * Returns how much budget is remaining. Can be negative if over budget.
     */
    public double getRemainingBudget(Trip trip) {
        return trip.getBudget() - getTotalSpent(trip);
    }

    /**
     * Returns true if the trip is over budget.
     */
    public boolean isOverBudget(Trip trip) {
        return getRemainingBudget(trip) < 0;
    }

    /**
     * Returns the total spent for a specific category.
     */
    public double getSpentByCategory(Trip trip, Category category) {
        double total = 0;
        for (Expense expense : trip.getExpenses()) {
            if (expense.getCategory() == category) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    // -------------------------------------------------------------------------
    // Display Helpers
    // -------------------------------------------------------------------------

    /**
     * Prints a full budget summary for a trip to the console.
     */
    public void printSummary(Trip trip) {
        double totalSpent = getTotalSpent(trip);
        double remaining  = getRemainingBudget(trip);

        System.out.println();
        System.out.println("========================================");
        System.out.println("  BUDGET SUMMARY: " + trip.getName());
        System.out.println("========================================");
        System.out.printf("  Total Budget:   $%.2f%n", trip.getBudget());
        System.out.printf("  Total Spent:    $%.2f%n", totalSpent);

        if (isOverBudget(trip)) {
            System.out.printf("  OVER BUDGET BY: $%.2f  ⚠%n", Math.abs(remaining));
        } else {
            System.out.printf("  Remaining:      $%.2f%n", remaining);
        }

        System.out.println();
        System.out.println("  --- Spending by Category ---");
        for (Category category : Category.values()) {
            double spent = getSpentByCategory(trip, category);
            if (spent > 0) {
                System.out.printf("  %-12s  $%.2f%n", category, spent);
            }
        }
        System.out.println("========================================");
        System.out.println();
    }
}
