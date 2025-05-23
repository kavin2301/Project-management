package dao;

import entity.Employee;
import entity.Project;
import entity.Task;
import java.util.List;
import java.time.LocalDate;
import entity.Expense;


public interface IProjectRepository {
    boolean createEmployee(Employee emp);
    boolean createProject(Project pj);
    boolean createTask(Task tk);
    boolean assignProjectToEmployee(int projectId, int employeeId);
    boolean assignTaskInProjectToEmployee(int taskId, int projectId, int employeeId);
    boolean deleteEmployee(int userId);
    boolean deleteProject(int projectId);
    List<Task> getAllTasks(int empId, int projectId);
    List<Project> searchProjectsByEmployeeId(int empId);
    List<Task> searchTasksByEmployeeId(int empId);
    List<Task> searchTasksByProjectId(int projectId);
    boolean addExpense(Expense expense);
    List<Expense> getExpensesByEmployeeAndDateRange(int empId, LocalDate start, LocalDate end);
    double getTotalExpensesByEmployeeAndDateRange(int empId, LocalDate start, LocalDate end);
}
