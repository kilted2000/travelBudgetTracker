package com.travelbudget.model;

/**
 * Represents a single expense on a trip.
 *
 * MVP fields: description, amount, category.
 * INTERMEDIATE upgrade ideas:
 *   - Add a 'date' field using LocalDate
 *   - Add a 'paidBy' field (String or Person object) for splitting
 *   - Add a 'currency' field for multi-currency support
 */
public class Expense {

    private String description;
    private double amount;
    private Category category;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public Expense(String description, double amount, Category category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    // -------------------------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------------------------

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // -------------------------------------------------------------------------
    // Display
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("  [%-12s] %-30s $%.2f", category, description, amount);
    }
}
