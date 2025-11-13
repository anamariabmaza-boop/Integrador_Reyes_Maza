package input;

import model.StatusTask;
import model.Task;

import java.util.List;

public interface FindTaskByProjectAndStatusInput {
    List<Task> findTasksByProjectAndStatus(Long idProject, StatusTask statusTask);
}
