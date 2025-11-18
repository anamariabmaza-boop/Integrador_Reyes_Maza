package com.undec.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.undec.persistence.entity.TaskData;
import model.Project;
import model.StatusProject;

import java.time.Clock;
import java.time.LocalDate;

public class ProjectRequest {
    @JsonProperty("id")
    private Long  id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("endDate")
    private LocalDate endDate;
    @JsonProperty("status")
    private StatusProject status;
    @JsonProperty("description")
    private String description;

    public ProjectRequest(){}

    public ProjectRequest(Long id, String name, LocalDate startDate, LocalDate endDate, StatusProject status, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;

    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
    public StatusProject getStatus() {return status;}
    public void setStatus(StatusProject status) {this.status = status;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Project toDomainProject (Clock clock){
        return Project.newProject(this.id, this.name, this.startDate,
                this.endDate, this.status, this.description, clock);
    }
}
