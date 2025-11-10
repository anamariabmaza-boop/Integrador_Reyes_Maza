package input;

import model.Task;
import model.statusTask;

import java.time.LocalDateTime;

public interface CreateTaskInput {
    Task createTask(Long idTask,
                    Long idProject,
                    Integer estimateHours,
                    String assign,
                    statusTask status,
                    LocalDateTime finishAt,
                    LocalDateTime createAt);
}
