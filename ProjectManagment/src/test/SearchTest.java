package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.ProjectRepositoryImpl;
import entity.Project;
import entity.Task;

import java.util.List;

public class SearchTest {

    @Test
    public void testSearchProjectByEmployeeId() {
        ProjectRepositoryImpl repo = new ProjectRepositoryImpl();
        List<Project> projects = repo.searchProjectsByEmployeeId(1);
        assertNotNull(projects);
        assertFalse(projects.isEmpty(), "Projects should be returned for valid employee ID");
    }

    @Test
    public void testSearchTaskByEmployeeId() {
        ProjectRepositoryImpl repo = new ProjectRepositoryImpl();
        List<Task> tasks = repo.searchTasksByEmployeeId(1);
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty(), "Tasks should be returned for valid employee ID");
    }
}
