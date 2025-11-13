package usecase;

import exception.ResourceNotFoundException;
import input.ExportProjectTaskInput;
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
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExportProjectTaskTest {
    @Mock
    ProjectRepository projectRepository;
    @Mock
    TaskRepository taskRepository;
    private final Clock clock = Clock.systemDefaultZone();
    Clock clockFijo = Clock.fixed(
            LocalDateTime.of(2025, 11, 9, 10, 0).toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
    );


    @Test
    public void Test_Successfull(){
        ExportProjectTaskInput useCase=new ExportProjectTaskUseCase(projectRepository,taskRepository);
        Project project = Project.newProject(
                17L,
                "ProjectChristmas",
                LocalDate.now(clock),
                LocalDate.now(clock).plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                clock
        );
        List<Task> taskList=new ArrayList<>();
        Task task1 = Task.newTask(
                1L,
                project,
                8,
                "mantecol",
                StatusTask.DONE,
                LocalDateTime.of(2025, 11, 9, 10, 0),
                LocalDateTime.of(2025, 11, 10, 10, 0)
        );
        Task task2 = Task.newTask(
                2L,
                project,
                8,
                "budin",
                StatusTask.DONE,
                LocalDateTime.of(2025, 11, 10, 11, 0),
                LocalDateTime.of(2025, 11, 9, 11, 0)
        );
        Task task3 = Task.newTask(
                3L,
                project,
                8,
                "panDulce",
                StatusTask.TODO,
                LocalDateTime.of(2025, 11, 10, 12, 0),
                LocalDateTime.of(2025, 11, 9, 12, 0)
        );

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        when(projectRepository.findProjectById(17L)).thenReturn(project);

        when(taskRepository.findByProject(project)).thenReturn(taskList);

        List<String> ListCSV= useCase.exportProjectTask(project.getProjectId());
        Assertions.assertEquals("Project: ProjectChristmas", ListCSV.get(0));
        Assertions.assertEquals("idTask,assignee,status,estimateHours", ListCSV.get(1));

        Assertions.assertEquals("1,mantecol,DONE,8", ListCSV.get(2));
        Assertions.assertEquals("2,budin,DONE,8", ListCSV.get(3));
        Assertions.assertEquals("3,panDulce,TODO,8", ListCSV.get(4));

        Assertions.assertNotNull(ListCSV);

    }
    @Test
    void useCase_UNsucessfull_ProyectNotExist(){
        ExportProjectTaskInput useCase=new ExportProjectTaskUseCase(projectRepository,taskRepository);

        when(projectRepository.findProjectById(91L)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class,()->useCase.exportProjectTask(91L));
    }
    @Test
    void useCase_UNsucessfull_ListEmpyt(){
        ExportProjectTaskInput useCase=new ExportProjectTaskUseCase(projectRepository,taskRepository);
        List<Task> taskList= new ArrayList<>();
        Project project = Project.newProject(
                17L,
                "ProjectChristmas",
                LocalDate.now(clock),
                LocalDate.now(clock).plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                clock
        );
        when(projectRepository.findProjectById(91L)).thenReturn(project);
        when(taskRepository.findByProject(project)).thenReturn(taskList);

        Assertions.assertThrows(ResourceNotFoundException.class,()->useCase.exportProjectTask(91L));
    }
}
