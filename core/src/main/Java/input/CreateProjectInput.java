package input;

import model.Project;
import model.Status;

import java.time.Clock;
import java.time.LocalDate;

public interface CreateProjectInput {
    Project createProject(Project project);
}
