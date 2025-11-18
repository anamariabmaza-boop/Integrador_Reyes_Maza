package com.undec.controller.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import model.Project;
import model.StatusTask;
import model.Task;

import java.time.LocalDateTime;

public class TaskRequest {

    @JsonProperty("idTask")
    private Long idTask;
    @JsonProperty("idTask")
    private Project project;
    //Agregar variable title
    @JsonProperty("idTask")
    private Integer estimateHours;
    @JsonProperty("idTask")
    private String assignee;
    @JsonProperty("idTask")
    private StatusTask status;
    @JsonProperty("idTask")
    private LocalDateTime finishedAt;
    @JsonProperty("idTask")
    private LocalDateTime createdAt;

    public TaskRequest(Long idTask, Project project, Integer estimateHours, String assignee, StatusTask status, LocalDateTime finishedAt, LocalDateTime createdAt) {
        this.idTask = idTask;
        this.project = project;
        this.estimateHours = estimateHours;
        this.assignee = assignee;
        this.status = status;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
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

    public Task toDomainTask(){
        return Task.newTask(this.idTask, this.project, this.estimateHours, this.assignee,
                this.status, this.finishedAt, this.createdAt);
    }


}
