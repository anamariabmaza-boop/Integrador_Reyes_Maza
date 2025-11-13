package usecase;

public class ProjectSummaryResponseModel {
    private final long idProject;
    private final String nameProject;
    private final int totalTask;
    private final int doneTask;
    private final double totalEstimateHours;

    public ProjectSummaryResponseModel(long idProject, String nameProject, int totalTask, int doneTask, double totalEstimateHours) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.totalTask = totalTask;
        this.doneTask = doneTask;
        this.totalEstimateHours = totalEstimateHours;
    }

    public long getIdProject() {
        return idProject;
    }
    public String getNameProject() {
        return nameProject;
    }
    public int getTotalTask() {
        return totalTask;
    }
    public int getDoneTask() {
        return doneTask;
    }
    public double getTotalEstimateHours() {
        return totalEstimateHours;
    }
}
