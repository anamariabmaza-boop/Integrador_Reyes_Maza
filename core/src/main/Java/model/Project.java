package model;
import exception.ValidationException;

import java.time.Clock;
import java.time.LocalDate;

public class Project {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private StatusProject projectStatus;
    private String description;

    private Project(Long id, String name, LocalDate startDate, LocalDate endDate, StatusProject projectStatus, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatus = projectStatus;
        this.description = description;
    }

    public static Project newProject(Long projectId,
                                     String name,
                                     LocalDate startDate,
                                     LocalDate endDate,
                                     StatusProject statusProject,
                                     String description,
                                     Clock clock) {
        if (projectId != null && projectId <= 0) {
            throw new ValidationException("The project ID can't be less than or equal to zero.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("The project name can't be null or empty.");
        }
        if (startDate == null || endDate == null) {
            throw new ValidationException("Start date and end date can't be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new ValidationException("The end date can't be before the start date.");
        }
        if (startDate.isBefore(LocalDate.now(clock))) {
            throw new ValidationException("The start date can't be in the past.");
        }
        if (statusProject == null) {
            throw new ValidationException("The project status can't be null.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new ValidationException("The project description can't be null or empty.");
        }
        return new Project(projectId, name, startDate, endDate, statusProject, description);
    }
    public Long getProjectId() {return id;}
    public String getName() {return name;}
    public LocalDate getStartDate() {return startDate;}
    public LocalDate getEndDate() {return endDate;}
    public StatusProject getProjectStatus() {return projectStatus;}
    public String getDescription() {return description;}
}
