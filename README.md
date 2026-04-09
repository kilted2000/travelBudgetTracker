# ✈️ Travel Budget Tracker

A Java CLI app to track expenses on a trip — built to grow from beginner to advanced.

---

## Project Structure

```
TravelBudgetTracker/
└── src/
    └── com/travelbudget/
        ├── Main.java                  ← Entry point
        ├── model/
        │   ├── Category.java          ← Enum: FOOD, HOTEL, TRANSPORT, ACTIVITIES, MISC
        │   ├── Expense.java           ← Single expense (description, amount, category)
        │   └── Trip.java              ← Trip with a budget and list of expenses
        ├── service/
        │   └── BudgetService.java     ← All calculations (total spent, remaining, by category)
        └── ui/
            └── MenuHandler.java       ← CLI menu and all user interaction
```

---

## How to Run

### From the terminal (plain Java, no IDE needed):

```bash
# 1. From the TravelBudgetTracker/ directory, compile all files:
javac -d out src/com/travelbudget/*.java src/com/travelbudget/**/*.java

# 2. Run:
java -cp out com.travelbudget.Main
```

### From IntelliJ IDEA or VS Code:
- Open the `TravelBudgetTracker/` folder as a project
- Mark `src/` as the sources root
- Run `Main.java`

---

## What the MVP Does

- Create a trip with a name and total budget
- Add expenses with a description, dollar amount, and category
- View all expenses in a formatted list
- View a budget summary showing:
  - Total spent
  - Remaining balance (or how much over budget you are)
  - Spending broken down by category
- Over-budget warnings after each expense

---

## Intermediate Upgrades (try these next)

### 1. Add dates to expenses
In `Expense.java`, add:
```java
import java.time.LocalDate;
private LocalDate date;
```
Update the constructor and `toString()`. In `MenuHandler`, prompt the user for a date
or default to `LocalDate.now()`.

### 2. Support multiple trips
Create a `TripManager.java` in the `service/` package:
```java
public class TripManager {
    private List<Trip> trips = new ArrayList<>();
    public void addTrip(Trip trip) { ... }
    public Trip getTripByName(String name) { ... }
    public List<Trip> getAllTrips() { ... }
}
```
Add a "Switch Trip" option to `MenuHandler`.

### 3. Save and load trips (File Persistence)
Create a `FileService.java` in `service/`:
- Save trips to a `.csv` file using `FileWriter`
- Load trips from the file on startup using `BufferedReader`
- Each line = one expense: `tripName,description,amount,category`

### 4. Per-category budget limits
In `Trip.java`, add:
```java
private HashMap<Category, Double> categoryLimits = new HashMap<>();
```
In `BudgetService`, add a `getCategoryStatus(Trip trip, Category category)` method
that checks spending against the limit for that category.

### 5. Filter expenses by category
In `MenuHandler`, add a menu option that asks for a category and prints
only expenses in that category.

---

## Advanced Upgrades

### Currency conversion
- Sign up for a free API key at https://app.exchangerate-api.com
- Use Java's `HttpClient` to call the API
- Store amounts in a home currency, convert on display

### Export to CSV
- Add an `ExportService.java` that writes all expenses to a `.csv` file
- Open it in Excel or Google Sheets to see your trip spending

### JavaFX Dashboard
- Replace the CLI with a visual UI
- Use a `TableView` for expenses
- Use `BarChart` for category spending visualization

---

## Java Concepts Covered (MVP)

| Concept | Where |
|---|---|
| Classes & Objects | `Trip`, `Expense` |
| Encapsulation | Private fields + getters/setters |
| Enum | `Category` |
| ArrayList | `Trip.expenses` |
| Loops | `BudgetService`, `MenuHandler` |
| Conditionals | Over-budget warnings |
| Exception handling | `promptInt`, `promptPositiveDouble` |
| String formatting | `toString()`, `printf` |
| Scanner / user input | `MenuHandler` |
| Separation of concerns | model / service / ui packages |
# travelBudgetTracker
