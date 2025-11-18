package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import model.Project;
import model.StatusProject;
import model.StatusTask;
import model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectRepository;
import output.TaskRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {


    @Mock
    ProjectRepository projectRepository;
    @Mock
    TaskRepository taskRepository;

    private final Clock clock = Clock.systemDefaultZone();

    @Test
    void test_useCaseCreateTask_Sucessfull(){
        CreateTaskUseCase useCase= new CreateTaskUseCase(clock,projectRepository,taskRepository);

        Project project= Project.newProject(91L,
                                            "P",
                                            LocalDate.now(clock),
                                            LocalDate.now(clock).plusDays(3),
                                            StatusProject.ACTIVE,
                                    "prueba",
                                            clock);
        when(projectRepository.findProjectById(91L)).thenReturn(project);

        when(taskRepository.saveTask(any(Task.class))).thenReturn(Task.newTask(12L,project,122,
                "juan", StatusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock).plusDays(3), "title"));

        Task task= useCase.createTask(12L,91L,122,
                "juan", StatusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock), "title");

        Assertions.assertNotNull(task);
    }
    @Test
    void useCase_UNsucessfull_ProyectNotExist(){
        CreateTaskUseCase useCase= new CreateTaskUseCase(clock,projectRepository,taskRepository);
      /*  Project project= Project.newProject(91L,"P", LocalDate.now(clock),LocalDate.now(clock).plusDays(3),
                Status.ACTIVE,"prueba");

       */
        when(projectRepository.findProjectById(91L)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class,()->useCase.createTask(12L,91L,122,
                "juan", StatusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock), "title"));
    }
    @Test
    void useCase_UNsucessfull_ProyectClose(){
        CreateTaskUseCase useCase= new CreateTaskUseCase(clock,projectRepository,taskRepository);
        Project project= Project.newProject(null,"P", LocalDate.now(clock),LocalDate.now(clock).plusDays(3),
                StatusProject.CLOSED,"prueba", clock);


        when(projectRepository.findProjectById(91L)).thenReturn(project);


        Assertions.assertThrows(BusinessRuleViolationException.class,()->useCase.createTask(12L,91L,122,
                "juan", StatusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock), "title"));
    }

}
