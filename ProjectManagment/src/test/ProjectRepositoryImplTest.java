package test;

import dao.ProjectRepositoryImpl;
import entity.Employee;
import entity.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRepositoryImplTest {

    ProjectRepositoryImpl repo = new ProjectRepositoryImpl();

    // 1. Test if employee is created successfully
    @Test
    public void testCreateEmployeeSuccess() {
        Employee emp = new Employee("John Test", "Developer", "Male", 60000.0, null);
        boolean result = repo.createEmployee(emp);
        assertTrue(result, "Employee should be created successfully");
    }

    // 2. Test if task is created successfully
    @Test
    public void testCreateTaskSuccess() {
        Task task = new Task("Unit Test Writing", 1, 1, "ASSIGNED"); // assumes project_id=1 and emp_id=1
        boolean result = repo.createTask(task);
        assertTrue(result, "Task should be created successfully");
    }

    // 3. Test searching for tasks assigned to employee in a project
    @Test
    public void testSearchTasksAssignedToEmployee() {
        List<Task> tasks = repo.getAllTasks(1, 1); // assumes emp_id=1 and project_id=1
        assertNotNull(tasks, "Returned task list should not be null");
        assertFalse(tasks.isEmpty(), "Task list should not be empty");
    }

    // 4. Test if exception handling works (e.g., insert task with missing values)
    @Test
    public void testCreateTaskFailureDueToNullValues() {
        Task invalidTask = new Task(null, 0, 0, null); // invalid task
        boolean result = repo.createTask(invalidTask);
        assertFalse(result, "Task creation should fail and return false");
    }
}