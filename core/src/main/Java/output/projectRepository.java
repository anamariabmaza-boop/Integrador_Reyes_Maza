package output;

import model.Project;

public interface projectRepository {
    Project findById(Long id);
}
