package model;

import exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Clock;
import java.time.LocalDate;

public class TestProject {

    //Instancia correcta con todos los atributos
    @Test
    void instanceProject_AllAttributes_InstanceCorrect() {
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(Clock.systemDefaultZone()),
                LocalDate.now(Clock.systemDefaultZone()).plusDays(5),
                Status.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        );

        Assertions.assertEquals(Long.valueOf(1L), project.getProjectId());
        Assertions.assertEquals("Website Redesign", project.getName());
        Assertions.assertEquals(Status.PLANNED, project.getProjectStatus());
        Assertions.assertEquals("Migrate and redesign website", project.getDescription());
        Assertions.assertEquals(LocalDate.now(Clock.systemDefaultZone()), project.getStartDate());
    }

    //Excepcion arrojada por "projectId" menor o igual a cero
    @Test
    void instanceProject_ProjectIdNegative_Exception() {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        -1L,
                        "Website Redesign",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        Status.PLANNED,
                        "Migrate and redesign website",
                        Clock.systemDefaultZone()
                ));
    }

    //No se lanza excepcion por "ProjectId" null
    @Test
    void instanceProject_ProjectIdNull_Allowed() {
        Assertions.assertDoesNotThrow(() -> Project.newProject(
                null,
                "Website Redesign",
                LocalDate.now(Clock.systemDefaultZone()),
                LocalDate.now(Clock.systemDefaultZone()).plusDays(5),
                Status.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        ));
    }

    //Excepcion arrojada por falta de "name"
    @ParameterizedTest
    @ValueSource(strings = {""})
    @NullSource
    void instanceProject_EmptyName_Exception(String name) {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        1L,
                        name,
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        Status.PLANNED,
                        "Migrate and redesign website",
                        Clock.systemDefaultZone()
                ));
    }

    //Excepcion arrojada por "startDate" null
    @Test
    void instanceProject_StartDateNull_Exception() {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        1L,
                        "Website Redesign",
                        null,
                        LocalDate.now().plusDays(5),
                        Status.PLANNED,
                        "Migrate and redesign website",
                        Clock.systemDefaultZone()
                ));
    }

    //Excepcion arrojada por "startDate" anterior a hoy
    @Test
    void instanceProject_StartDateBeforeToday_Exception() {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        1L,
                        "Website Redesign",
                        LocalDate.now(Clock.systemDefaultZone()).minusDays(1),
                        LocalDate.now(Clock.systemDefaultZone()).plusDays(5),
                        Status.PLANNED,
                        "Migrate and redesign website",
                        Clock.systemDefaultZone()
                ));
    }

    //Excepcion arrojada por "endDate" null
    @Test
    void instanceProject_EndDateNull_Exception() {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        1L,
                        "Website Redesign",
                        LocalDate.now(Clock.systemDefaultZone()),
                        null,
                        Status.PLANNED,
                        "Migrate and redesign website",
                        Clock.systemDefaultZone()
                ));
    }

    //Excepcion arrojada por "endDate" anterior a startDate
    @Test
    void instanceProject_EndDateBeforeStartDate_Exception() {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        1L,
                        "Website Redesign",
                        LocalDate.now(Clock.systemDefaultZone()).plusDays(3),
                        LocalDate.now(Clock.systemDefaultZone()),
                        Status.PLANNED,
                        "Migrate and redesign website",
                        Clock.systemDefaultZone()
                ));
    }

    //Excepcion arrojada por "description" vacia o nula
    @ParameterizedTest
    @ValueSource(strings = {""})
    @NullSource
    void instanceProject_EmptyDescription_Exception(String description) {
        Assertions.assertThrows(ValidationException.class,
                () -> Project.newProject(
                        1L,
                        "Website Redesign",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        Status.PLANNED,
                        description,
                        Clock.systemDefaultZone()
                ));
    }

    //Solucionar test para clock

}
