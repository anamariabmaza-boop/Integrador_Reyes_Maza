package input;

import model.Project;
import usecase.ProjectSummaryResponseModel;

public interface GetProjectSummaryInput {

    ProjectSummaryResponseModel getProjectSummary(long idProject);
}
