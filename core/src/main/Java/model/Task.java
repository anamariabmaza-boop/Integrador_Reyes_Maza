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
    private final String title;

    private Task(Long idTask,
                 Project project,
                 Integer estimateHours,
                 String assignee,
                 StatusTask status,
                 LocalDateTime finishedAt,
                 LocalDateTime createdAt, String title) {
        this.idTask = idTask;
        this.project = project;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
        this.title = title;
    }

    public static Task newTask(Long idTask,
                               Project project,
                               Integer estimateHours,
                               String assignee,
                               StatusTask status,
                               LocalDateTime finishedAt,
                               LocalDateTime createdAt,
                               String title) {

        if (idTask == null || idTask <= 0) {
            throw new ValidationException("The task id can't be null or less than zero");
        }

        if (estimateHours == null || estimateHours <= 0) {
            throw new ValidationException("The estimate hours can't be null or less than zero");
        }

        if (status == null) {
            throw new ValidationException("The task status can't be null.");
        }

        // finishedAt debe ser null excepto cuando la tarea está completada
        finishedAt = null;
        if (status == StatusTask.DONE) {
            finishedAt = LocalDateTime.now();
        }

        return new Task(idTask, project, estimateHours, assignee, status, finishedAt, createdAt, title);
    }

    public Long getIdTask() {
        return idTask;
    }

    public Project getProject() {
        return project;
    }

    public Integer getEstimateHours() {
        return estimateHours;
    }

    public String getAssignee() {
        return assignee;
    }

    public StatusTask getStatus() {
        return status;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getTitle() {
        return title;
    }

}
