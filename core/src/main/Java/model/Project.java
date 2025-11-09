package model;

import exception.ValidationException;

import java.time.LocalDate;
//Crear proyecto es como importar driver
public class Project {
    private final Long projectId;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final status projectStatus;
    private final String description;


    private Project(Long projectId, String name, LocalDate startDate, LocalDate endDate, status projectStatus, String description) {
        this.projectId = projectId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatus = projectStatus;
        this.description = description;
    }

    public static Project newProject (Long projectId, String name, LocalDate startDate, LocalDate endDate, status projectStatus, String description){
        if(projectId == null || projectId <= 0){
            throw new ValidationException("The project ID can't be null or less than or equal to zero.");
        }
        if(name == null || name.isEmpty()){
            throw new ValidationException("The project name can't be null or empty.");
        }
        if(startDate.isBefore(LocalDate.now())){
            throw new ValidationException("The project start date can't be null or it cannot be before the current date");
        }
        if(endDate == null || endDate.isBefore(startDate)){
            throw new ValidationException("The project end date can't be null or it cannot be before the project creation date");
        }
        return new Project(projectId, name, startDate, endDate, projectStatus, description);
    }

    public Long getProjectId() {
        return projectId;
    }
    public String getName() {return  name;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public status getProjectStatus() {return projectStatus;}
    public String getDescription() {return description;}
}

