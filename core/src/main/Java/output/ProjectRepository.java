package output;

import model.Project;

public interface ProjectRepository {
boolean existsByName (String name);
Project save (Project project);
}
