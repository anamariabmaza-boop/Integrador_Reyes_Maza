package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import input.CreateTaskInput;
import model.Project;
import model.StatusTask;
import model.Task;
import output.ProjectRepository;
import output.TaskRepository;

import java.time.Clock;
import java.time.LocalDateTime;

public class CreateTaskUseCase implements CreateTaskInput {

    private final Clock clock;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(Clock clock, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.clock = clock;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;

    }

    @Override
    public Task createTask(Long idTask, Long idProject, Integer estimateHours, String assign, StatusTask status, LocalDateTime finishAt, LocalDateTime createAt) {

        Project project = projectRepository.findProjectById(idProject);
        if(project == null){
            throw new ResourceNotFoundException("Project not exist");
        }
        if(project.getProjectStatus()== model.StatusProject.CLOSED){
            throw new BusinessRuleViolationException("Project is already closed");
        }

        Task task= Task.newTask(idTask,project,estimateHours,assign,status,finishAt,createAt);

        Task savedTask=taskRepository.saveTask(task);

        if(savedTask==null){
            throw new BusinessRuleViolationException("Task not saved");
        }
        return savedTask;
    }
}
