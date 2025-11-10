package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import model.Project;
import model.Task;
import model.status;
import model.statusTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.projectRepository;
import output.taskRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class test_useCaseCreateTask {


    @Mock
    projectRepository projectRepository;
    @Mock
    taskRepository taskRepository;


    private final Clock clock = Clock.systemDefaultZone();
    @Test
    void test_useCaseCreateTask_Sucessfull(){
        CreateTaskUseCase useCase= new CreateTaskUseCase(clock,projectRepository,taskRepository);
        Project project= Project.newProject(91L,"P", LocalDate.now(clock),LocalDate.now(clock).plusDays(3),
                status.ACTIVE,"prueba");
        when(projectRepository.findById(91L)).thenReturn(project);

        when(taskRepository.saveTask(any(Task.class))).thenReturn(Task.newTask(12L,project,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock).plusDays(3)));

        Task task= useCase.createTask(12L,91L,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock));

        Assertions.assertNotNull(task);
    }
    @Test
    void useCase_UNsucessfull_ProyectNotExist(){
        CreateTaskUseCase useCase= new CreateTaskUseCase(clock,projectRepository,taskRepository);
      /*  Project project= Project.newProject(91L,"P", LocalDate.now(clock),LocalDate.now(clock).plusDays(3),
                status.ACTIVE,"prueba");

       */
        when(projectRepository.findById(91L)).thenReturn(null);

        /*
        when(taskRepository.saveTask(any(Task.class))).thenReturn(Task.newTask(12L,project,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock).plusDays(3)));

        Task task= useCase.createTask(12L,91L,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock));


         */
        Assertions.assertThrows(ResourceNotFoundException.class,()->useCase.createTask(12L,91L,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock)));
    }
    @Test
    void useCase_UNsucessfull_ProyectClose(){
        CreateTaskUseCase useCase= new CreateTaskUseCase(clock,projectRepository,taskRepository);
       Project project= Project.newProject(91L,"P", LocalDate.now(clock),LocalDate.now(clock).plusDays(3),
                status.CLOSED,"prueba");


        when(projectRepository.findById(91L)).thenReturn(project);

        /*
        when(taskRepository.saveTask(any(Task.class))).thenReturn(Task.newTask(12L,project,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock).plusDays(3)));

        Task task= useCase.createTask(12L,91L,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock));
         */
        Assertions.assertThrows(BusinessRuleViolationException.class,()->useCase.createTask(12L,91L,122,
                "juan", statusTask.IN_PROGRESS, LocalDateTime.now(clock),LocalDateTime.now(clock)));
    }



}
