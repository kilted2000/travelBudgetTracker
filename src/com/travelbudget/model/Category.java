package com.travelbudget.model;

/**
 * Expense categories for a trip.
 * To add a new category later, just add a new constant here.
 */
public enum Category {
    FOOD,
    HOTEL,
    TRANSPORT,
    ACTIVITIES,
    MISC;

    /**
     * Prints a numbered menu of all categories for CLI selection.
     */
    public static void printMenu() {
        Category[] values = Category.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println("  " + (i + 1) + ". " + values[i]);
        }
    }

    /**
     * Returns a Category by 1-based menu index.
     * Returns MISC if the index is out of range.
     */
    public static Category fromMenuIndex(int index) {
        Category[] values = Category.values();
        if (index >= 1 && index <= values.length) {
            return values[index - 1];
        }
        return MISC;
    }
}
