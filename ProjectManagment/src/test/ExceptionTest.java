package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.ProjectRepositoryImpl;
import myexceptions.EmployeeNotFoundException;
import myexceptions.ProjectNotFoundException;

public class ExceptionTest {

    @Test
    public void testEmployeeNotFoundException() {
        ProjectRepositoryImpl repo = new ProjectRepositoryImpl();
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            repo.searchProjectsByEmployeeId(999); // assuming 999 doesn't exist
        });
        assertEquals("Employee not found with id: 999", exception.getMessage());
    }

    @Test
    public void testProjectNotFoundException() {
        ProjectRepositoryImpl repo = new ProjectRepositoryImpl();
        Exception exception = assertThrows(ProjectNotFoundException.class, () -> {
            repo.searchTasksByProjectId(999); // assuming 999 doesn't exist
        });
        assertEquals("Project not found with id: 999", exception.getMessage());
    }
}
