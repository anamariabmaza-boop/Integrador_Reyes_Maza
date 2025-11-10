package usecase;

import exception.DuplicateResourceException;
import exception.ValidationException;
import model.Project;
import output.ProjectRepository;
import input.CreateProjectInput;

import java.time.Clock;

public class CreateProjectUseCase implements CreateProjectInput{
    private final ProjectRepository projectRepository;
    private final Clock  clock;

    public CreateProjectUseCase(ProjectRepository projectRepository, Clock clock) {
        this.projectRepository = projectRepository;
        this.clock = clock;
    }

    public Project createProject (Project project){
        //validar las reglas del negocio, unicidad, que el nombre sea unico
        if(projectRepository.existsByName(project.getName())){
            throw new DuplicateResourceException("Project already exists");
        }
        //construir y validar el dominio
        try{
            //Se construye el objeto project y se aplican las validaciones de fecha y null
            Project validProject = Project.newProject(
                    null, project.getName(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getProjectStatus(),
                    project.getDescription(),
                    this.clock
            );
            return projectRepository.save(validProject);
        } catch (ValidationException e) {
            throw e;
        }
    }

}
