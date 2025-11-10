package model;

import exception.ValidationException;

import java.time.LocalDateTime;

public class Task {
    private final Long idTask;
    private final Project project;
    private final Integer estimateHours;
    private final String assignee;
    private final StatusTask status;
    private final LocalDateTime finishedAt;
    private final LocalDateTime createdAt;

    private Task(Long idTask, Project project, Integer estimateHours, String assignee, StatusTask status, LocalDateTime finishedAt, LocalDateTime createdAt) {
        this.idTask = idTask;
        this.project = project;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
    }
    public static Task newTask(Long idTask, Project project, Integer estimateHours, String assignee, StatusTask status, LocalDateTime finishedAt, LocalDateTime createdAt){
        if(idTask == null || idTask <= 0){
            throw new ValidationException("The task id can't be null or less than zero");
        }
        if(estimateHours == null || estimateHours <= 0){
            throw new ValidationException("The estimate hours can't be null or less than zero");
        }
        if(status == null){
            throw new ValidationException("The task status can't be null.");
        }
        if(finishedAt.isBefore(LocalDateTime.now())){
            throw new ValidationException("The task finishedAt can't be before the now.");
        }
        return new Task(idTask, project, estimateHours, assignee, status, finishedAt, createdAt);
    }
    public Long getIdTask() {return idTask;}
    public Project getProject() {return project;}
    public Integer getEstimateHours() {return estimateHours;}
    public String getAssignee() {return assignee;}
    public StatusTask getStatus() {return status;}
    public LocalDateTime getFinishedAt() {return finishedAt;}
    public LocalDateTime getCreatedAt() {return createdAt;}
}
