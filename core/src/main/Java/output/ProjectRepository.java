package output;

import model.Project;

public interface ProjectRepository {
Project findProjectById(Long id);
boolean existsByName (String name);
Project save (Project project);
}
