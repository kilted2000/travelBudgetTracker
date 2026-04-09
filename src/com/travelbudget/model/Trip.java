package com.travelbudget.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single trip with a name, a budget, and a list of expenses.
 *
 * INTERMEDIATE upgrade ideas:
 *   - Add 'startDate' and 'endDate' fields using LocalDate
 *   - Add a 'currency' field (e.g. "USD", "EUR")
 *   - Add per-category budget limits (HashMap<Category, Double>)
 *   - Add a list of travelers (List<String>) for splitting expenses
 */
public class Trip {

    private String name;
    private double budget;
    private List<Expense> expenses;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public Trip(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = new ArrayList<>();
    }

    // -------------------------------------------------------------------------
    // Expense Management
    // -------------------------------------------------------------------------

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public boolean hasExpenses() {
        return !expenses.isEmpty();
    }

    // -------------------------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    // -------------------------------------------------------------------------
    // Display
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Trip: %-20s | Budget: $%.2f", name, budget);
    }
}
