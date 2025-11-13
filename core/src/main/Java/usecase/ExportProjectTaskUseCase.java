package usecase;

import exception.ResourceNotFoundException;
import input.ExportProjectTaskInput;
import model.Project;
import model.Task;
import output.ProjectRepository;
import output.TaskRepository;

import java.util.List;
import java.util.stream.Stream;

public class ExportProjectTaskUseCase implements ExportProjectTaskInput {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ExportProjectTaskUseCase(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<String> exportProjectTask(long idProject) {
        Project project=projectRepository.findProjectById(idProject);
        if(project==null){
            throw new ResourceNotFoundException("Project not found");
        }
        List<Task> projectTask=taskRepository.findByProject(project);

        if(projectTask.isEmpty()){
            throw new ResourceNotFoundException("Task is empty");
        }
        String projectName= project.getName();
        String header="idTask,assignee,status,estimateHours";

        List<String> listTask= projectTask.stream().map(s->String.join(",",
                s.getIdTask().toString(),
                s.getAssignee(),
                s.getStatus().name(),
                String.valueOf(s.getEstimateHours())
                )).toList();

        List<String> outputCSV = Stream.concat(
                Stream.of("Project: " + projectName, header),
                listTask.stream()
        ).toList();

    return outputCSV;
    }
}
