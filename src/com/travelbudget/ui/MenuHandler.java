package com.travelbudget.ui;

import com.travelbudget.model.Category;
import com.travelbudget.model.Expense;
import com.travelbudget.model.Trip;
import com.travelbudget.service.BudgetService;
import java.util.Scanner;

/**
 * Handles all user interaction via the command line.
 * Keeping UI logic here keeps Main.java clean and makes it
 * easy to swap CLI for a GUI or web interface later.
 *
 * INTERMEDIATE upgrade ideas:
 *   - Add a "switch trip" option once TripManager is built
 *   - Add input validation with a helper method (validatePositiveDouble)
 *   - Add an "edit expense" or "delete expense" option
 */
public class MenuHandler {

    private final Scanner scanner;
    private final BudgetService budgetService;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public MenuHandler() {
        this.scanner = new Scanner(System.in);
        this.budgetService = new BudgetService();
    }

    // -------------------------------------------------------------------------
    // Entry Point
    // -------------------------------------------------------------------------

    /**
     * Starts the application. Creates a trip then enters the main menu loop.
     */
    public void start() {
        printWelcome();
        Trip trip = createTrip();
        runMainMenu(trip);
        System.out.println("\nSafe travels! Have a great time! ✈");
        scanner.close();
    }

    // -------------------------------------------------------------------------
    // Trip Setup
    // -------------------------------------------------------------------------

    private Trip createTrip() {
        System.out.println("\n--- CREATE YOUR TRIP ---");
        System.out.print("Trip name: ");
        String name = scanner.nextLine().trim();

        double budget = promptPositiveDouble("Total budget ($): ");
        Trip trip = new Trip(name, budget);

        System.out.printf("%nTrip \"%s\" created with a $%.2f budget!%n", name, budget);
        return trip;
    }

    // -------------------------------------------------------------------------
    // Main Menu Loop
    // -------------------------------------------------------------------------

    private void runMainMenu(Trip trip) {
        boolean running = true;

        while (running) {
            printMainMenu(trip);
            int choice = promptInt("Choice: ");

            switch (choice) {
                case 1 -> addExpense(trip);
                case 2 -> viewExpenses(trip);
                case 3 -> budgetService.printSummary(trip);
                case 4 -> running = false;
                default -> System.out.println("Invalid option. Please enter 1–4.");
            }
        }
    }

    // -------------------------------------------------------------------------
    // Menu Options
    // -------------------------------------------------------------------------

    private void addExpense(Trip trip) {
        System.out.println("\n--- ADD EXPENSE ---");
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        double amount = promptPositiveDouble("Amount ($): ");

        System.out.println("Category:");
        Category.printMenu();
        int categoryChoice = promptInt("Choose category (1–" + Category.values().length + "): ");
        Category category = Category.fromMenuIndex(categoryChoice);

        Expense expense = new Expense(description, amount, category);
        trip.addExpense(expense);

        System.out.printf("Added: %s%n", expense);

        // Warn if now over budget
        if (budgetService.isOverBudget(trip)) {
            System.out.printf("⚠  Warning: You are now OVER BUDGET by $%.2f!%n",
                    Math.abs(budgetService.getRemainingBudget(trip)));
        } else {
            System.out.printf("   Remaining budget: $%.2f%n",
                    budgetService.getRemainingBudget(trip));
        }
    }

    private void viewExpenses(Trip trip) {
        System.out.println("\n--- EXPENSES: " + trip.getName() + " ---");

        if (!trip.hasExpenses()) {
            System.out.println("No expenses logged yet.");
            return;
        }

        for (Expense expense : trip.getExpenses()) {
            System.out.println(expense);
        }

        System.out.printf("%n  TOTAL SPENT: $%.2f%n", budgetService.getTotalSpent(trip));
    }

    // -------------------------------------------------------------------------
    // Display Helpers
    // -------------------------------------------------------------------------

    private void printWelcome() {
        System.out.println("========================================");
        System.out.println("       TRAVEL BUDGET TRACKER ✈         ");
        System.out.println("========================================");
    }

    private void printMainMenu(Trip trip) {
        double remaining = budgetService.getRemainingBudget(trip);
        String budgetStatus = budgetService.isOverBudget(trip)
                ? String.format("OVER by $%.2f ⚠", Math.abs(remaining))
                : String.format("$%.2f remaining", remaining);

        System.out.println("\n----------------------------------------");
        System.out.println("  Trip: " + trip.getName() + "  |  " + budgetStatus);
        System.out.println("----------------------------------------");
        System.out.println("  1. Add Expense");
        System.out.println("  2. View All Expenses");
        System.out.println("  3. View Budget Summary");
        System.out.println("  4. Exit");
        System.out.println("----------------------------------------");
    }

    // -------------------------------------------------------------------------
    // Input Helpers
    // -------------------------------------------------------------------------

    /**
     * Prompts for an int. Re-prompts if input is not a valid integer.
     */
    private int promptInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    /**
     * Prompts for a positive double. Re-prompts if input is invalid or <= 0.
     */
    private double promptPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0) return value;
                System.out.println("Amount must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g. 45.50).");
            }
        }
    }
}
