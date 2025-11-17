package persistence.entity;

import jakarta.persistence.*;

import model.StatusTask;

import java.time.LocalDateTime;
@Entity
public class TaskData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;

    @ManyToOne
    @JoinColumn(name = "Project_id")
    private ProjectData project;
    @Column(name = "Estimate_Hours")
    private Integer estimateHours;
    @Column(name = "Assignee")
    private String assignee;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusTask status;
    @Column(name = "Finished_At")
    private LocalDateTime finishedAt;
    @Column(name = "Create_At")
    private LocalDateTime createdAt;

    public TaskData() {
    }

    public TaskData(ProjectData project, Integer estimateHours, String assignee, StatusTask status, LocalDateTime finishedAt, LocalDateTime createdAt) {

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

    public ProjectData getProject() {
        return project;
    }

    public void setProject(ProjectData project) {
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
}
