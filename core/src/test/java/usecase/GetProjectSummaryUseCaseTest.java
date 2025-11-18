package usecase;

import exception.ResourceNotFoundException;
import input.GetProjectSummaryInput;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProjectSummaryUseCaseTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    TaskRepository taskRepository;
    private final Clock clock = Clock.systemDefaultZone();

    @Test
    public void getProjectSummaryTest_Successfull(){
        GetProjectSummaryInput useCase=new GetProjectSummaryUseCase(projectRepository,taskRepository);
        Project project = Project.newProject(
                17L,
                "Website Redesign",
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
                LocalDateTime.now(),
                LocalDateTime.now(),
                "title");
        Task task2 = Task.newTask(
                2L,
                project,
                8,
                "budin",
                StatusTask.DONE,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "title");
        Task task3 = Task.newTask(
                3L,
                project,
                8,
                "panDulce",
                StatusTask.TODO,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "title");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        when(projectRepository.findProjectById(17L)).thenReturn(project);

        when(taskRepository.findByProject(project.getProjectId())).thenReturn(taskList);

        ProjectSummaryResponseModel projectSummary= useCase.getProjectSummary(project.getProjectId());

        Assertions.assertNotNull(projectSummary);
        Assertions.assertEquals(projectSummary.getTotalTask(),3);
        Assertions.assertEquals(projectSummary.getDoneTask(),2);
        Assertions.assertEquals(projectSummary.getTotalEstimateHours(),24);
    }
    @Test
    public void getProjectSummaryTest_ProjectNotFound(){
        GetProjectSummaryInput useCase=new GetProjectSummaryUseCase(projectRepository,taskRepository);
        Project project = Project.newProject(
                17L,
                "Website Redesign",
                LocalDate.now(clock),
                LocalDate.now(clock).plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                clock
        );

        when(projectRepository.findProjectById(17L)).thenReturn(null);

        Assertions.assertThrows(ResourceNotFoundException.class,()->useCase.getProjectSummary(project.getProjectId()));
    }
    @Test
    public void getProjectSummaryTest_ListTaskEmpty(){
        GetProjectSummaryInput useCase=new GetProjectSummaryUseCase(projectRepository,taskRepository);
        Project project = Project.newProject(
                17L,
                "Website Redesign",
                LocalDate.now(clock),
                LocalDate.now(clock).plusDays(5),
                StatusProject.PLANNED,
                "Migrate and redesign website",
                clock
        );
        List<Task> taskList=new ArrayList<>();
        when(projectRepository.findProjectById(17L)).thenReturn(project);

        when(taskRepository.findByProject(project.getProjectId())).thenReturn(taskList);

        Assertions.assertThrows(ResourceNotFoundException.class,()->useCase.getProjectSummary(project.getProjectId()));
    }
}
