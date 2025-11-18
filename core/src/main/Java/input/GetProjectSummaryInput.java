package input;


import usecase.ProjectSummaryResponseModel;

public interface GetProjectSummaryInput {

    ProjectSummaryResponseModel getProjectSummary(long idProject);
}
