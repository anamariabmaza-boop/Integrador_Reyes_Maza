package usecase;

import exception.BusinessRuleViolationException;
import exception.ResourceNotFoundException;
import exception.ValidationException;
import input.FindTaskByProjectAndStatusInput;
import model.Project;
import model.StatusTask;
import model.Task;
import output.ProjectRepository;
import output.TaskRepository;

import java.util.List;

public class FindTasksByProjectAndStatusUseCase implements FindTaskByProjectAndStatusInput {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public FindTasksByProjectAndStatusUseCase(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTasksByProjectAndStatus(Long idProject, StatusTask statusTask) {

        //Validar los parametros
        if (idProject <= 0) {
            throw new ValidationException("Project ID cannot be null or less than zero.");
        }
        if (statusTask == null) {
            throw new ValidationException("Task status cannot be null.");
        }

        //Buscar el proyecto por ID
        Project project = projectRepository.findProjectById(idProject);

        //si el proyecto no existe, lanzar excepcion
        if (project == null) {
            throw new ResourceNotFoundException("Project not found with ID: " + idProject);
        }

        //buscar todas las tareas asociadas al proyecto
        List<Task> allTasks = taskRepository.findByProject(project);

        //si no hay tareas, devolvemos una lista vacía (en vez de error)
        if (allTasks == null || allTasks.isEmpty()) {
            throw  new BusinessRuleViolationException("The list of tasks is empty.");
        }

        //filtrar las tareas segun su status
        List<Task> filteredTasks = allTasks.stream()
                .filter(task -> task.getStatus() == statusTask)
                .toList();

        //retornar la lista filtrada
        return filteredTasks;
    }

}
