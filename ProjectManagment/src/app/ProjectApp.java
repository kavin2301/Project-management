package app;

import dao.ProjectRepositoryImpl;
import util.DBConnUtil;
import entity.Employee;
import entity.Project;
import entity.Task;
import entity.Expense;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class ProjectApp {
    public static void main(String[] args) {
        // Initialize DB connection
        DBConnUtil.getConnection("db.properties");

        ProjectRepositoryImpl repo = new ProjectRepositoryImpl();

        try (Scanner sc = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n--- Project Management Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. Add Project");
                System.out.println("3. Add Task");
                System.out.println("4. Assign Project to Employee");
                System.out.println("5. Assign Task in Project to Employee");
                System.out.println("6. Delete Employee");
                System.out.println("7. Delete Task");
                System.out.println("8. List All Tasks for Employee in Project");
                System.out.println("9. Add Expense");
                System.out.println("10. View Expenses by Employee and Date Range");
                System.out.println("11. View Total Expenses by Employee and Date Range");
                System.out.println("12. Exit");

                System.out.print("Enter your choice: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a number: ");
                    sc.next();
                }

                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter employee name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter designation: ");
                        String designation = sc.nextLine();
                        System.out.print("Enter gender (Male/Female/Other): ");
                        String gender = sc.nextLine();
                        System.out.print("Enter salary: ");
                        double salary = sc.nextDouble();
                        System.out.print("Enter project ID (optional, 0 for none): ");
                        int projectId = sc.nextInt();
                        sc.nextLine();

                        Employee emp = new Employee(name, designation, gender, salary, projectId == 0 ? null : projectId);
                        System.out.println(repo.createEmployee(emp)
                                ? "Employee added successfully!"
                                : "Failed to add employee.");
                        break;

                    case 2:
                        System.out.print("Enter project name: ");
                        String projectName = sc.nextLine();
                        System.out.print("Enter project description: ");
                        String description = sc.nextLine();
                        System.out.print("Enter project start date (yyyy-mm-dd): ");
                        String startDate = sc.nextLine();
                        System.out.print("Enter project status (started/dev/build/test/deployed): ");
                        String status = sc.nextLine();

                        Project project = new Project(projectName, description, startDate, status);
                        System.out.println(repo.createProject(project)
                                ? "Project added successfully!"
                                : "Failed to add project.");
                        break;

                    case 3:
                        System.out.print("Enter task name: ");
                        String taskName = sc.nextLine();
                        System.out.print("Enter project ID: ");
                        int taskProjectId = sc.nextInt();
                        System.out.print("Enter employee ID: ");
                        int taskEmployeeId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter task status (Assigned/Started/Completed): ");
                        String taskStatus = sc.nextLine();

                        Task task = new Task(taskName, taskProjectId, taskEmployeeId, taskStatus);
                        System.out.println(repo.createTask(task)
                                ? "Task added successfully!"
                                : "Failed to add task.");
                        break;

                    case 4:
                        System.out.print("Enter employee ID: ");
                        int empId = sc.nextInt();
                        System.out.print("Enter project ID: ");
                        int projId = sc.nextInt();
                        System.out.println(repo.assignProjectToEmployee(projId, empId)
                                ? "Project assigned successfully!"
                                : "Failed to assign project.");
                        break;

                    case 5:
                        System.out.print("Enter task ID: ");
                        int taskId = sc.nextInt();
                        System.out.print("Enter project ID: ");
                        int projId2 = sc.nextInt();
                        System.out.print("Enter employee ID: ");
                        int empId2 = sc.nextInt();
                        System.out.println(repo.assignTaskInProjectToEmployee(taskId, projId2, empId2)
                                ? "Task assigned successfully!"
                                : "Failed to assign task.");
                        break;

                    case 6:
                        System.out.print("Enter employee ID: ");
                        int deleteEmpId = sc.nextInt();
                        System.out.println(repo.deleteEmployee(deleteEmpId)
                                ? "Employee deleted successfully!"
                                : "Failed to delete employee.");
                        break;

                    case 7:
                        System.out.print("Enter task ID: ");
                        int deleteTaskId = sc.nextInt();
                        System.out.println(repo.deleteTask(deleteTaskId)
                                ? "Task deleted successfully!"
                                : "Failed to delete task.");
                        break;

                    case 8:
                        System.out.print("Enter employee ID: ");
                        int empIdForTasks = sc.nextInt();
                        System.out.print("Enter project ID: ");
                        int projIdForTasks = sc.nextInt();
                        List<Task> taskList = repo.getAllTasks(empIdForTasks, projIdForTasks);
                        if (taskList.isEmpty()) {
                            System.out.println("No tasks found.");
                        } else {
                            taskList.forEach(t -> System.out.println("- " + t.getTaskName()));
                        }
                        break;

                    case 9:
                        System.out.print("Enter employee ID: ");
                        int empIdExpense = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter expense category: ");
                        String category = sc.nextLine();
                        System.out.print("Enter amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        String dateStr = sc.nextLine();
                        try {
                            LocalDate expenseDate = LocalDate.parse(dateStr);
                            Expense exp = new Expense(empIdExpense, category, amount, expenseDate);
                            System.out.println(repo.addExpense(exp)
                                    ? "Expense added successfully!"
                                    : "Failed to add expense.");
                        } catch (Exception e) {
                            System.out.println("Invalid date format.");
                        }
                        break;

                    case 10:
                        System.out.print("Enter employee ID: ");
                        int empIdRange = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter start date (yyyy-mm-dd): ");
                        String startRange = sc.nextLine();
                        System.out.print("Enter end date (yyyy-mm-dd): ");
                        String endRange = sc.nextLine();
                        try {
                            LocalDate start = LocalDate.parse(startRange);
                            LocalDate end = LocalDate.parse(endRange);
                            List<Expense> expenses = repo.getExpensesByEmployeeAndDateRange(empIdRange, start, end);
                            if (expenses.isEmpty()) {
                                System.out.println("No expenses found.");
                            } else {
                                System.out.println("--- Expenses ---");
                                expenses.forEach(e -> System.out.printf("Date: %s | Category: %s | Amount: ₹%.2f%n",
                                        e.getDate(), e.getCategory(), e.getAmount()));
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid date format.");
                        }
                        break;

                    case 11:
                        System.out.print("Enter employee ID: ");
                        int empIdTotal = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter start date (yyyy-mm-dd): ");
                        String startStrTotal = sc.nextLine();
                        System.out.print("Enter end date (yyyy-mm-dd): ");
                        String endStrTotal = sc.nextLine();
                        try {
                            LocalDate startDateTotal = LocalDate.parse(startStrTotal);
                            LocalDate endDateTotal = LocalDate.parse(endStrTotal);
                            double total = repo.getTotalExpensesByEmployeeAndDateRange(empIdTotal, startDateTotal, endDateTotal);
                            System.out.println("--------------------------------------------");
                            System.out.printf("Total expenses for Employee ID %d from %s to %s: ₹%.2f%n",
                                    empIdTotal, startDateTotal, endDateTotal, total);
                            System.out.println("--------------------------------------------");
                        } catch (Exception e) {
                            System.out.println("Invalid date format.");
                        }
                        break;

                    case 12:
                        System.out.println("Exiting application. Goodbye!");
                        DBConnUtil.closeConnection();
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 12);
        }
    }
}
