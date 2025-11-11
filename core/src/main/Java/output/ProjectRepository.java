package output;

import model.Project;

public interface ProjectRepository {
Project findProjectById(int id);
boolean existsByName (String name);
Project save (Project project);
}
