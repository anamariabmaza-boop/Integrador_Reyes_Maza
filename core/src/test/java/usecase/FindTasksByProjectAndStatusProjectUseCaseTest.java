package usecase;

import exception.ResourceNotFoundException;
import exception.ValidationException;
import model.Project;
import model.StatusProject;
import model.StatusTask;
import model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectRepository;
import output.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindTasksByProjectAndStatusProjectUseCaseTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private TaskRepository taskRepository;

    @Test
    void findTasksByProjectAndStatus_ProjectExists_ReturnTasksFiltered() {
        Project project = Project.newProject(
                1L,
                "Website Resign",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                StatusProject.PLANNED,
                "Migrate and ...",
                java.time.Clock.systemDefaultZone()
        );

        Task task1 = Task.newTask(1L, project, 8, "alice",
                StatusTask.TODO, null, LocalDateTime.now(), "title");
        Task task2 = Task.newTask(2L, project, 4, "ana",
                StatusTask.DONE, null, LocalDateTime.now(), "title");
        Task task3 = Task.newTask(3L, project, 6, "romina",
                StatusTask.TODO, null, LocalDateTime.now(), "title");

        when(projectRepository.findProjectById(1L)).thenReturn(project);
        when(taskRepository.findByProject(project.getProjectId())).thenReturn(List.of(task1, task2, task3));

        FindTasksByProjectAndStatusUseCase useCase =
                new FindTasksByProjectAndStatusUseCase(projectRepository, taskRepository);

        List<Task> result = useCase.findTasksByProjectAndStatus(1L, StatusTask.TODO);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(task -> task.getStatus() == StatusTask.TODO));


    }
    @Test
    void findTasksByProjectAndStatus_ProjectIdNegative_ThrowsResourceNotFoundException() {
        FindTasksByProjectAndStatusUseCase useCase =
                new FindTasksByProjectAndStatusUseCase(projectRepository, taskRepository);
        when(projectRepository.findProjectById(-1L)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () ->
                useCase.findTasksByProjectAndStatus(-1L, StatusTask.TODO)
        );
    }
    @Test
    void findTasksByProjectAndStatus_StatusNull_ThrowsValidatoinException() {
        FindTasksByProjectAndStatusUseCase useCase = new FindTasksByProjectAndStatusUseCase
                (projectRepository, taskRepository);
        Project project = Project.newProject(
                1L,
                "Website Resign",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                StatusProject.PLANNED,
                "Migrate and ...",
                java.time.Clock.systemDefaultZone()
        );
        when(projectRepository.findProjectById(1L)).thenReturn(project);

        assertThrows(ValidationException.class, () ->
                useCase.findTasksByProjectAndStatus(1L, null));

    }

}
