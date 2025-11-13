package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import input.GetProjectSummaryInput;
import model.Project;
import model.StatusTask;
import model.Task;
import output.ProjectRepository;
import output.TaskRepository;

import java.util.List;
import java.util.function.Predicate;

public class GetProjectSummaryUseCase implements GetProjectSummaryInput {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public GetProjectSummaryUseCase(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ProjectSummaryResponseModel getProjectSummary(long idProject) {

        Project project= projectRepository.findProjectById(idProject);
        if(project==null){
            throw new ResourceNotFoundException("Project not exist");
        }

        List<Task> allTaskProject=taskRepository.findByProject(project);

        if(allTaskProject.isEmpty()){
            throw new ResourceNotFoundException("List of tasks is Empty");
        }
        int totalTask=allTaskProject.size();
        Predicate<Task> pred=s->s.getStatus().compareTo(StatusTask.DONE)==0;
        //realizo un casteo para obtener int ya que .count() devuelve un long
        int doneTask=(int) allTaskProject.stream().filter(pred).count();

        double totalEstimateHours= allTaskProject.stream().mapToDouble(Task::getEstimateHours).sum();


        return new ProjectSummaryResponseModel(project.getProjectId(),
                                                project.getName(),
                                                totalTask,
                                                doneTask,
                                                totalEstimateHours);
    }
}
