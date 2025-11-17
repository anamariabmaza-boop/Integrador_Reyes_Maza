package output;

import model.Project;
import model.StatusTask;
import model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findByProject(Long id);
    Task saveTask(Task task);
}
