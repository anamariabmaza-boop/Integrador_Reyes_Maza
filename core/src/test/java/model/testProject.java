package model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class testProject {
    @Test
    //Happy path
    void newProject_atributosValidos() {
        Project newProject = Project.newProject(
                123456700L, "FirstProject",
                LocalDate.of(2025, 11, 8 ),
                LocalDate.of(2025, 12, 10), status.PLANNED,
                "Start of the firts project"
        );

        Assertions.assertNotNull(newProject);
        Assertions.assertEquals(123456700L, newProject.getProjectId());
        Assertions.assertEquals("FirstProject", newProject.getName());
        Assertions.assertEquals(LocalDate.of(2025, 11, 8), newProject.getStartDate());
        Assertions.assertEquals(LocalDate.of(2025, 12, 10), newProject.getEndDate());
        Assertions.assertEquals(status.PLANNED, newProject.getProjectStatus());
        Assertions.assertEquals("Start of the firts project", newProject.getDescription());

    }
    @Test
    void createProject_idProjectException() {
    }
    @Test
    void createProject_nameException() {
    }

    @Test
    void createProject_starDateException() {
    }
    @Test
    void createProject_endDateException() {
    }

}
