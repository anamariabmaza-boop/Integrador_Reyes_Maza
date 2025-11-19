package com.undec.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Project;
import model.StatusTask;
import model.Task;

import java.time.LocalDateTime;

public class TaskResponse {

    @JsonProperty("idTask")
    private Long idTask;
    @JsonProperty("project")
    private Project project;
    //Agregar variable title
    @JsonProperty("estimateHours")
    private Integer estimateHours;
    @JsonProperty("assignee")
    private String assignee;
    @JsonProperty("status")
    private StatusTask status;
    @JsonProperty("finishedAt")
    private LocalDateTime finishedAt;
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    @JsonProperty("title")
    private String title;

    public TaskResponse(Long idTask, Project project, Integer estimateHours, String assignee, StatusTask status, LocalDateTime finishedAt, LocalDateTime createdAt, String title) {
        this.idTask = idTask;
        this.project = project;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
        this.title = title;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getEstimateHours() {
        return estimateHours;
    }

    public void setEstimateHours(Integer estimateHours) {
        this.estimateHours = estimateHours;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static TaskResponse fromDomainTask(Task task){
        return new TaskResponse(task.getIdTask(), task.getProject(), task.getEstimateHours(),
                task.getAssignee(), task.getStatus(), task.getFinishedAt(), task.getCreatedAt(), task.getTitle());
    }
}
