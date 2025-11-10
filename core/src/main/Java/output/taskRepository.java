package output;

import model.Task;

public interface taskRepository {
    Task saveTask(Task task);
}
