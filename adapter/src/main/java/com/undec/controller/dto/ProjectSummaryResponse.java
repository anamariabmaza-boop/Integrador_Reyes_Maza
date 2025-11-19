package com.undec.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Project;
import usecase.ProjectSummaryResponseModel;

public class ProjectSummaryResponse {

    @JsonProperty("idProject")
    private Long idProject;
    @JsonProperty("nameProject")
    private String nameProject;
    @JsonProperty("totalTask")
    private  int totalTask;
    @JsonProperty("doneTask")
    private int doneTask;
    @JsonProperty("totalEstimateHours")
    private double totalEstimateHours;


    public ProjectSummaryResponse(Long idProject, String nameProject, int totalTask, int doneTask, double totalEstimateHours) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.totalTask = totalTask;
        this.doneTask = doneTask;
        this.totalEstimateHours = totalEstimateHours;
    }

    public Long getIdProject() {
        return idProject;
    }
    public void setIdProject(Long idProject) {}
    public String getNameProject() {
        return nameProject;
    }
    public void setNameProject(String nameProject) {}
    public int getTotalTask() {
        return totalTask;
    }
    public void setTotalTask(int totalTask) {}
    public int getDoneTask() {
        return doneTask;
    }
    public void setDoneTask(int doneTask) {}
    public double getTotalEstimateHours() {
        return totalEstimateHours;
    }
    public void setTotalEstimateHours(double totalEstimateHours) {}

    public static  ProjectSummaryResponse fromDomainProjectSummary (ProjectSummaryResponseModel summaryResponseModel){
        return new ProjectSummaryResponse(summaryResponseModel.getIdProject(),
                summaryResponseModel.getNameProject(), summaryResponseModel.getTotalTask(),
                summaryResponseModel.getDoneTask(), summaryResponseModel.getTotalEstimateHours());
    }
    {}
}
