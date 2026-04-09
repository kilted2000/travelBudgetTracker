package com.travelbudget;

import com.travelbudget.ui.MenuHandler;

/**
 * Travel Budget Tracker — Beginner MVP
 *
 * Entry point. Intentionally tiny — all logic lives in
 * MenuHandler, BudgetService, Trip, and Expense.
 *
 * To run from the command line:
 *   1. Compile:  javac -d out src/com/travelbudget/**\/*.java
 *   2. Run:      java -cp out com.travelbudget.Main
 */
public class Main {

    public static void main(String[] args) {
        MenuHandler menu = new MenuHandler();
        menu.start();
    }
}
