package entity;

import java.time.LocalDate;

public class Expense {
    private int expenseId;
    private int employeeId;
    private String category;         // Added
    private double amount;
    private LocalDate expenseDate;

    // Constructors
    public Expense() {}

    public Expense(int employeeId, String category, double amount, LocalDate expenseDate) {
        this.employeeId = employeeId;
        this.category = category;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    // Getters and Setters
    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getCategory() {     // Added
        return category;
    }

    public void setCategory(String category) {   // Added
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {      // Changed to match usage in ProjectApp.java
        return expenseDate;
    }

    public void setDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
}