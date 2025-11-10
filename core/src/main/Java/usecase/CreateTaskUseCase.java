package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import input.CreateTaskInput;
import model.Project;
import model.Task;
import model.statusTask;
import output.projectRepository;
import output.taskRepository;

import java.time.Clock;
import java.time.LocalDateTime;

public class CreateTaskUseCase implements CreateTaskInput {

    private final Clock clock;
    private final projectRepository projectRepository;
    private final taskRepository taskRepository;

    public CreateTaskUseCase(Clock clock, projectRepository projectRepository, taskRepository taskRepository) {
        this.clock = clock;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;

    }

    @Override
    public Task createTask(Long idTask, Long idProject, Integer estimateHours, String assign, statusTask status, LocalDateTime finishAt, LocalDateTime createAt) {

        Project project = projectRepository.findById(idProject);
        if(project == null){
            throw new ResourceNotFoundException("Project not exist");
        }
        if(project.getProjectStatus()== model.status.CLOSED){
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
