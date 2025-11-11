package model;

import exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class TestTask {
    // Instancia correcta con todos los atributos
    @Test
    void instanceTask_AllAttributes_InstanceCorrect() {
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                StatusProject.PLANNED,
                "Algo",
                Clock.systemDefaultZone()
        );

        LocalDateTime now = LocalDateTime.now();

        Task task = Task.newTask(
                12L,
                project,
                123,
                "alice",
                StatusTask.TODO,
                now,
                now
        );

        Assertions.assertEquals(12L, task.getIdTask());
        Assertions.assertEquals(project, task.getProject());
        Assertions.assertEquals(123, task.getEstimateHours());
        Assertions.assertEquals("alice", task.getAssignee());
        Assertions.assertEquals(StatusTask.TODO, task.getStatus());
        Assertions.assertEquals(now, task.getCreatedAt());
        Assertions.assertNull(task.getFinishedAt());
    }


    // Excepcion arrojada por "idTask" menor o igual a cero
    @Test
    void instanceTask_idTaskNegative_Exception() {
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        );

        Assertions.assertThrows(ValidationException.class, () ->
                Task.newTask(
                        -1L,
                        project,
                        10,
                        "alice",
                        StatusTask.TODO,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
    }
    // Excepción arrojada por "estimateHours" nulo o menor o igual a cero
    @ParameterizedTest
    @ValueSource(ints = {0, -10})
    @NullSource
    void instanceTask_estimateHoursInvalid_Exception(Integer estimateHours) {
        // Crear un proyecto válido para asociar a la tarea
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        );

        Assertions.assertThrows(ValidationException.class, () ->
                Task.newTask(
                        1L,
                        project,
                        estimateHours,
                        "alice",
                        StatusTask.TODO,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
    }
    //Excepcion lanzada si "status" es null
    @Test
    void instanceTask_statusNull_Exception() {
        // Primero creamos un proyecto válido para asociar a la tarea
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        );

       Assertions.assertThrows(ValidationException.class, () ->
                Task.newTask(
                        -1L,
                        project,
                        10,
                        "alice",
                        null,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
    }


    @Test
    void instanceTask_StatusNotDone_FinishedAtNull() {
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        );

        Task task = Task.newTask(
                10L,
                project,
                8,
                "bob",
                StatusTask.TODO,  // NO es DONE
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Assertions.assertNull(task.getFinishedAt(),
                "finishedAt debe ser null cuando el estado no es DONE");
    }

    @Test
    void instanceTask_StatusDone_FinishedAtAssigned() {
        Project project = Project.newProject(
                1L,
                "Website Redesign",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                Clock.systemDefaultZone()
        );

        LocalDateTime beforeCreation = LocalDateTime.now();

        Task task = Task.newTask(
                11L,
                project,
                5,
                "alice",
                StatusTask.DONE,  // Es DONE
                null,
                LocalDateTime.now()
        );

        LocalDateTime afterCreation = LocalDateTime.now();

        Assertions.assertNotNull(task.getFinishedAt(),
                "finishedAt debe asignarse automáticamente cuando el estado es DONE");

        Assertions.assertTrue(
                !task.getFinishedAt().isBefore(beforeCreation) &&
                        !task.getFinishedAt().isAfter(afterCreation),
                "finishedAt debe estar dentro del rango temporal en que se creó la tarea"
        );
    }


}
